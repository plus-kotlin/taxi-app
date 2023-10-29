package com.plus.taxiapp.domain.payment

import com.plus.taxiapp.domain.payment.account.Account
import com.plus.taxiapp.domain.payment.card.Card
import com.plus.taxiapp.domain.payment.command.PaymentMethodCommand
import com.plus.taxiapp.domain.payment.paymentMethod.PaymentMethod
import com.plus.taxiapp.infra.client.ValidationClient
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.logging.Logger

@Service
class PaymentMethodService(
    private val paymentMethodRepository: PaymentMethodRepository,
    private val validationClient: ValidationClient,
) {
    private val logger: Logger = Logger.getLogger("PaymentMethodService")

    @Transactional
    fun addPaymentMethod(command: PaymentMethodCommand.Add): PaymentMethod {
        val (memberId, paymentMethodType, accountInfo, cardInfo, isDefault) = command
        val newPaymentMethod = when (paymentMethodType) {
            PaymentMethod.Type.ACCOUNT -> PaymentMethod(
                memberId = memberId,
                paymentMethodType = PaymentMethod.Type.ACCOUNT,
                card = null,
                account = accountInfo?.let {
                    Account(
                        accountNum = it.accountNum,
                        accountPassword = it.accountPassword,
                        accountHolder = it.accountHolder,
                        accountHolderInfo = it.accountHolderInfo,
                        bankName = it.bankName,
                        isVerified = validationClient.validationAccount(it.accountNum, it.accountPassword),
                    )
                },
                isDefault = isDefault,
            )

            PaymentMethod.Type.CARD -> PaymentMethod(
                memberId = memberId,
                paymentMethodType = PaymentMethod.Type.ACCOUNT,
                card = cardInfo?.let {
                    Card(
                        cardNum = it.cardNum,
                        cardPassword = it.cardPassword,
                        expirationDate = it.expirationDate,
                        cvc = it.cvc,
                        bankName = it.bankName,
                        isVerified = validationClient.validationCard(
                            it.cardNum,
                            it.cardPassword,
                            it.cvc,
                            it.expirationDate
                        ),
                    )
                },
                account = null,
                isDefault = isDefault,
            )
        }

        val paymentMethods = paymentMethodRepository.findPaymentMethodsByMemberId(memberId)
        val defaultMethod = paymentMethods.getDefaultMethod()
        if (newPaymentMethod.checkNeedDefaultUpdate(defaultMethod) && defaultMethod != null) {
            paymentMethodRepository.updateDefault(defaultMethod.offDefault())
        }

        return paymentMethodRepository.savePaymentMethod(newPaymentMethod)
    }
}
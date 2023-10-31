package com.plus.taxiapp.domain.payment

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
        val (memberId, paymentMethodType, accountInfo, cardInfo) = command
        val newPaymentMethod = when (paymentMethodType) {
            PaymentMethod.Type.ACCOUNT ->
                accountInfo?.let {
                    PaymentMethod.of(
                        command,
                        validationClient.validateAccount(it.accountNum, it.accountPassword)
                    )
                } ?: throw NullPointerException("Not Found Account Info")
            PaymentMethod . Type . CARD ->
                cardInfo?.let {
                    PaymentMethod.of(
                        command,
                        validationClient.validateCard(it.cardNum, it.cardPassword, it.cvc, it.expirationDate)
                    )
                } ?: throw NullPointerException("Not Found Card Info")
        }

        val paymentMethods = paymentMethodRepository.findPaymentMethodsByMemberId(memberId)
        val defaultMethod = paymentMethods.getDefaultMethod()
        if (newPaymentMethod.checkNeedDefaultUpdate(defaultMethod) && defaultMethod != null) {
            paymentMethodRepository.updateDefault(defaultMethod.offDefault())
        }

        return paymentMethodRepository.savePaymentMethod(newPaymentMethod)
    }
}
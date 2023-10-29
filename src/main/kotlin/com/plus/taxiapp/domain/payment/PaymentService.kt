package com.plus.taxiapp.domain.payment

import com.plus.taxiapp.domain.payment.paymentMethod.PaymentMethod
import com.plus.taxiapp.infra.client.PaymentClient
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PaymentService(
    private val paymentMethodRepository: PaymentMethodRepository,
    private val paymentClient: PaymentClient,
) {
    private val logger: Logger = Logger.getLogger("PaymentService")

    fun paymentFare(memberId: Long) {
        val paymentMethods = paymentMethodRepository.findPaymentMethodsByMemberId(memberId)
        val defaultPaymentMethod = paymentMethods.getDefaultMethod()
        when (defaultPaymentMethod?.paymentMethodType) {
            PaymentMethod.Type.ACCOUNT -> {
                val account =
                    defaultPaymentMethod.account ?: throw NullPointerException("Not Found ${defaultPaymentMethod.memberId} Account")
                paymentClient.paymentByAccount(account.accountNum, account.accountPassword)
            }

            PaymentMethod.Type.CARD -> {
                val card = defaultPaymentMethod.card ?: throw NullPointerException("Not Found ${defaultPaymentMethod.memberId} Card")
                paymentClient.paymentByCard(
                    card.cardNum,
                    card.cardPassword,
                    card.cvc,
                    card.expirationDate
                )
            }
            else -> {throw NullPointerException("Not Found Default Payment Method")}
        }
    }
}
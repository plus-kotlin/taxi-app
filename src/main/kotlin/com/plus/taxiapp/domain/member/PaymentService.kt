package com.plus.taxiapp.domain.member

import com.plus.taxiapp.domain.member.command.PaymentCommand
import com.plus.taxiapp.infra.middleware.payment.PaymentMiddleWare
import com.plus.taxiapp.infra.middleware.validation.ValidationMiddleWare
import org.springframework.stereotype.Service

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository,
    private val validationMiddleWare: ValidationMiddleWare,
    private val paymentMiddleWare: PaymentMiddleWare,
) {
    fun registerAccount(request: PaymentCommand.RegisterAccount): Account {
        validationMiddleWare.validationAccount(request.accountNum, request.accountPassword)
        return paymentRepository.saveAccount(
            Account(
                memberId = request.memberId!!,
                accountNum = request.accountNum,
                accountPassword = request.accountPassword,
                accountHolder = request.accountHolder,
                accountHolderInfo = request.accountHolderInfo,
                bankName = request.bankName,
                isDefault = request.isDefault,
                isVerified = true,
            )
        )
    }

    fun registerCard(request: PaymentCommand.RegisterCard): Card {
        validationMiddleWare.validationCard(request.cardNum, request.cardPassword, request.cvc!!, request.expirationDate)
        return paymentRepository.saveCard(
            Card(
                memberId = request.memberId!!,
                cardNum = request.cardNum,
                cardPassword = request.cardPassword,
                expirationDate = request.expirationDate,
                cvc = request.cvc,
                bankName = request.bankName,
                isDefault = request.isDefault,
                isVerified = true,
            )
        )
    }

    fun paymentFare(paymentType: PaymentType?, paymentId: Long?) {
        when (paymentType) {
            PaymentType.ACCOUNT -> {
                val findAccount = paymentRepository.findAccount(paymentId!!)
                paymentMiddleWare.paymentByAccount(findAccount.accountNum, findAccount.accountPassword)
            }
            PaymentType.CARD -> {
                val findCard = paymentRepository.findCard(paymentId!!)
                paymentMiddleWare.paymentByCard(findCard.cardNum, findCard.cardPassword, findCard.cvc, findCard.expirationDate)
            }
            else -> throw IllegalArgumentException("Payment Type Info Error")
        }
    }
}

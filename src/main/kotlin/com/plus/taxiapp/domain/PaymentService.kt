package com.plus.taxiapp.domain

import com.plus.taxiapp.domain.command.PaymentCommand
import com.plus.taxiapp.infra.middleware.validation.ValidationMiddleWare
import org.springframework.stereotype.Service

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository,
    private val validationMiddleWare: ValidationMiddleWare,
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
}

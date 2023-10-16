package com.plus.taxiapp.domain

import com.plus.taxiapp.domain.command.PaymentCommand
import com.plus.taxiapp.infra.middleware.validation.ValidationMiddleWare
import org.springframework.stereotype.Service

@Service
class MemberPaymentService(
    private val memberPaymentRepository: MemberPaymentRepository,
    private val validationMiddleWare: ValidationMiddleWare,
) {
    fun registerAccount(request: PaymentCommand.RegisterAccount): Account {
        validationMiddleWare.validationAccount(request.accountNum, request.accountPassword)
        return memberPaymentRepository.saveAccount(
            Account(
                userId = request.userId,
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
}

package com.plus.taxiapp.domain

import com.plus.taxiapp.domain.command.PaymentCommand
import org.springframework.stereotype.Service

@Service
class MemberPaymentService {
    fun registerAccount(request: PaymentCommand.RegisterAccount): Account {
        return Account(
            userId = request.userId,
            accountNum = request.accountNum,
            accountHolder = request.accountHolder,
            accountHolderInfo = request.accountHolderInfo,
            bankName = request.bankName,
            isDefault = request.isDefault,
            isVerified = true,
        )
    }
}

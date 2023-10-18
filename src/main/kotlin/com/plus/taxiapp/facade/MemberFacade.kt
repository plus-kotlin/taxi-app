package com.plus.taxiapp.facade

import com.plus.taxiapp.domain.Account
import com.plus.taxiapp.domain.PaymentService
import com.plus.taxiapp.domain.MemberService
import com.plus.taxiapp.domain.PaymentType
import com.plus.taxiapp.domain.command.PaymentCommand
import org.springframework.stereotype.Component

@Component
class MemberFacade(
    private val paymentService: PaymentService,
    private val memberService: MemberService,
) {
    fun registerAccount(request: PaymentCommand.RegisterAccount): Account {
        val registerAccount = paymentService.registerAccount(request)
        memberService.updateDefaultPayment(memberId = registerAccount.memberId, type = PaymentType.ACCOUNT, paymentId = registerAccount.id!!)
        return registerAccount
    }
}
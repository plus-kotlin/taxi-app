package com.plus.taxiapp.facade

import com.plus.taxiapp.domain.*
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

    fun registerCard(request: PaymentCommand.RegisterCard): Card {
        val registerCard = paymentService.registerCard(request)
        memberService.updateDefaultPayment(memberId = registerCard.memberId, type = PaymentType.CARD, paymentId = registerCard.id!!)
        return registerCard
    }
}
package com.plus.taxiapp.facade

import com.plus.taxiapp.domain.member.*
import com.plus.taxiapp.domain.member.command.PaymentCommand
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

@Component
class MemberFacade(
    private val paymentService: PaymentService,
    private val memberService: MemberService,
) {
    fun registerAccount(request: PaymentCommand.RegisterAccount): Account {
        val registerAccount = paymentService.registerAccount(request)
        if (registerAccount.isDefault) {
            memberService.updateDefaultPayment(
                memberId = registerAccount.memberId,
                type = PaymentType.ACCOUNT,
                paymentId = registerAccount.id!!
            )
        }
        return registerAccount
    }

    fun registerCard(request: PaymentCommand.RegisterCard): Card {
        val registerCard = paymentService.registerCard(request)
        if (registerCard.isDefault) {
            memberService.updateDefaultPayment(
                memberId = registerCard.memberId,
                type = PaymentType.CARD,
                paymentId = registerCard.id!!
            )
        }
        return registerCard
    }

    fun paymentFare(memberId: Long, drivingRecordId: Long, fare: Double): LocalDateTime {
        // TODO("운행 기록(DrivingRecord) 확인(조회)")

        val findMember = memberService.findMember(memberId)
        if(findMember.defaultPaymentId == null) {
            throw NullPointerException("Not Found Payment Method Info")
        }

        // 결제 진행
        paymentService.paymentFare(findMember.defaultPaymentType, findMember.defaultPaymentId)

        // TODO("운행 기록(DrivingRecord)의 상태를 결제 완료 상태로 update")
        return LocalDateTime.now()
    }
}
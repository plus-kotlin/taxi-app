package com.plus.taxiapp.facade

import com.plus.taxiapp.domain.member.MemberService
import com.plus.taxiapp.domain.payment.PaymentService
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class PaymentFacade(
    private val paymentService: PaymentService,
    private val memberService: MemberService,
) {
    fun paymentFare(drivingRecordId: Long, memberId: Long, fare: Double): LocalDateTime {
        // TODO("운행 기록(DrivingRecord) 확인(조회)")

//        val findMember = memberService.findMember(memberId)
//        if(findMember.defaultPaymentId == null) {
//            throw NullPointerException("Not Found Payment Method Info")
//        }
//
//        // 결제 진행
//        paymentService.paymentFare(findMember.defaultPaymentType, findMember.defaultPaymentId)

        // TODO("운행 기록(DrivingRecord)의 상태를 결제 완료 상태로 update")
        return LocalDateTime.now()
    }
}
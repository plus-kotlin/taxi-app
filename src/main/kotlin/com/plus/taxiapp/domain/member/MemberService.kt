package com.plus.taxiapp.domain.member

import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {
    fun updateDefaultPayment(memberId: Long, type: PaymentType, paymentId: Long): Member {
        return memberRepository.updateDefaultPayment(memberId, type, paymentId)
    }

    fun findMember(memberId: Long): Member {
        return memberRepository.findMember(memberId)
    }
}
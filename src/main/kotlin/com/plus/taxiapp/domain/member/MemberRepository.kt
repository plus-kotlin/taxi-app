package com.plus.taxiapp.domain.member

interface MemberRepository {
    fun updateDefaultPayment(memberId: Long, type: PaymentType, paymentId: Long): Member
    fun findMember(memberId: Long): Member
}
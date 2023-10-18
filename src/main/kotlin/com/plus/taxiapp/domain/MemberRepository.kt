package com.plus.taxiapp.domain

interface MemberRepository {
    fun updateDefaultPayment(memberId: Long, type: PaymentType, paymentId: Long): Member
}
package com.plus.taxiapp.infra.store.member

import com.plus.taxiapp.domain.Member
import com.plus.taxiapp.domain.MemberRepository
import com.plus.taxiapp.domain.PaymentType
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class MemberRepositoryImpl(
    private val memberJpaRepository: MemberJpaRepository,
): MemberRepository {
    override fun updateDefaultPayment(memberId: Long, type: PaymentType, paymentId: Long): Member {
        val findMember = memberJpaRepository.findByIdOrNull(memberId) ?: throw NullPointerException("Not Found Member")
        findMember.defaultPaymentType = type
        findMember.defaultPaymentId = paymentId

        val updateMember = memberJpaRepository.save(findMember)
        return Member(
            id = updateMember.id,
            name = updateMember.name,
            defaultPaymentType = updateMember.defaultPaymentType,
            defaultPaymentId = updateMember.defaultPaymentId,
        )
    }
}
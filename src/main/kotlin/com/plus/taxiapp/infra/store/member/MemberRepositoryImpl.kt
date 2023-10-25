package com.plus.taxiapp.infra.store.member

import com.plus.taxiapp.domain.member.Account
import com.plus.taxiapp.domain.member.Member
import com.plus.taxiapp.domain.member.MemberRepository
import com.plus.taxiapp.domain.member.PaymentType
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class MemberRepositoryImpl(
    private val memberJpaRepository: MemberJpaRepository,
): MemberRepository {
    override fun updateDefaultPayment(memberId: Long, type: PaymentType, paymentId: Long): Member {
        val findMember = findMemberEntity(memberId)
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

    @Transactional(readOnly = true)
    override fun findMember(memberId: Long): Member {
        val findMember = findMemberEntity(memberId)
        return Member(
            id = findMember.id,
            name = findMember.name,
            defaultPaymentType = findMember.defaultPaymentType,
            defaultPaymentId = findMember.defaultPaymentId,
            account = findMember.accountList?.map {
                Account(
                    id = it.id,
                    accountNum = it.accountNum,
                    accountPassword = it.accountPassword,
                    accountHolder = it.accountHolder,
                    accountHolderInfo = it.accountHolderInfo,
                    bankName = it.bankName,
                    isDefault = it.isDefault,
                    isVerified = it.isVerified,
                    memberId = it.memberEntity.id!!
                    )
            }?.toMutableList()
        )
    }

    fun findMemberEntity(memberId: Long): MemberEntity {
        return memberJpaRepository.findByIdOrNull(memberId) ?: throw NullPointerException("Not Found Member")
    }
}
package com.plus.taxiapp.infra.store.payment

import com.plus.taxiapp.domain.Account
import com.plus.taxiapp.domain.MemberPaymentRepository
import com.plus.taxiapp.infra.store.member.MemberJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
 class MemberPaymentRepositoryImpl(
     private val accountJpaRepository: AccountJpaRepository,
     private val memberJpaRepository: MemberJpaRepository,
 ): MemberPaymentRepository {
    override fun saveAccount(account: Account): Account {
        val memberId = account.memberId
        val findMember = memberJpaRepository.findByIdOrNull(memberId) ?: throw NullPointerException("Not Found Member")
        val saveMember = accountJpaRepository.save(AccountEntity(
            accountNum = account.accountNum,
            accountPassword = account.accountPassword,
            accountHolder = account.accountHolder,
            accountHolderInfo = account.accountHolderInfo,
            bankName = account.bankName,
            isDefault = account.isDefault,
            isVerified = account.isVerified,
            memberEntity = findMember,
        ))

        return Account(
            memberId = saveMember.memberEntity.id,
            accountNum = saveMember.accountNum,
            accountPassword = saveMember.accountPassword,
            accountHolder = saveMember.accountHolder,
            accountHolderInfo = saveMember.accountHolderInfo,
            bankName = saveMember.bankName,
            isDefault = saveMember.isDefault,
            isVerified = saveMember.isVerified,
        )
    }
}
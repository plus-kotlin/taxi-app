package com.plus.taxiapp.infra.store.payment

import com.plus.taxiapp.domain.Account
import com.plus.taxiapp.domain.PaymentRepository
import com.plus.taxiapp.infra.store.member.MemberJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
 class PaymentRepositoryImpl(
     private val accountJpaRepository: AccountJpaRepository,
     private val memberJpaRepository: MemberJpaRepository,
 ): PaymentRepository {
    override fun saveAccount(account: Account): Account {
        val memberId = account.memberId
        val findMember = memberJpaRepository.findByIdOrNull(memberId) ?: throw NullPointerException("Not Found Member")
        val saveAccount = accountJpaRepository.save(AccountEntity(
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
            id = saveAccount.id,
            memberId = saveAccount.memberEntity.id!!,
            accountNum = saveAccount.accountNum,
            accountPassword = saveAccount.accountPassword,
            accountHolder = saveAccount.accountHolder,
            accountHolderInfo = saveAccount.accountHolderInfo,
            bankName = saveAccount.bankName,
            isDefault = saveAccount.isDefault,
            isVerified = saveAccount.isVerified,
        )
    }
}
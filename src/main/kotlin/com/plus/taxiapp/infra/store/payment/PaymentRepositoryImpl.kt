package com.plus.taxiapp.infra.store.payment

import com.plus.taxiapp.domain.Account
import com.plus.taxiapp.domain.Card
import com.plus.taxiapp.domain.PaymentRepository
import com.plus.taxiapp.infra.store.member.MemberEntity
import com.plus.taxiapp.infra.store.member.MemberJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
 class PaymentRepositoryImpl(
     private val accountJpaRepository: AccountJpaRepository,
     private val cardJpaRepository: CardJpaRepository,
     private val memberJpaRepository: MemberJpaRepository,
 ): PaymentRepository {
    override fun saveAccount(account: Account): Account {
        val findMember = findMemberEntity(account.memberId)
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

    override fun saveCard(card: Card): Card {
        val findMember = findMemberEntity(card.memberId)
        val saveCard = cardJpaRepository.save(CardEntity(
            cardNum = card.cardNum,
            cardPassword = card.cardPassword,
            expirationDate = card.expirationDate,
            cvc = card.cvc,
            bankName = card.bankName,
            isDefault = card.isDefault,
            isVerified = card.isVerified,
            memberEntity = findMember,
        ))

        return Card(
            id = saveCard.id,
            memberId = saveCard.memberEntity.id!!,
            cardNum = saveCard.cardNum,
            cardPassword = saveCard.cardPassword,
            expirationDate = saveCard.expirationDate,
            cvc = saveCard.cvc,
            bankName = saveCard.bankName,
            isDefault = saveCard.isDefault,
            isVerified = saveCard.isVerified,
        )
    }

    private fun findMemberEntity(memberId: Long): MemberEntity {
        return memberJpaRepository.findByIdOrNull(memberId) ?: throw NullPointerException("Not Found Member")
    }

}
package com.plus.taxiapp.infra.store.payment

import com.plus.taxiapp.domain.member.Account
import com.plus.taxiapp.domain.member.Card
import com.plus.taxiapp.domain.member.PaymentRepository
import com.plus.taxiapp.infra.store.member.MemberEntity
import com.plus.taxiapp.infra.store.member.MemberJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class PaymentRepositoryImpl(
    private val accountJpaRepository: AccountJpaRepository,
    private val cardJpaRepository: CardJpaRepository,
    private val memberJpaRepository: MemberJpaRepository,
) : PaymentRepository {
    override fun saveAccount(account: Account): Account {
        val findMember = findMemberEntity(account.memberId)
        if (!account.isDefault && findMember.defaultPaymentId == null) {
            account.isDefault = true
        }
        val saveAccount = accountJpaRepository.save(
            AccountEntity(
                accountNum = account.accountNum,
                accountPassword = account.accountPassword,
                accountHolder = account.accountHolder,
                accountHolderInfo = account.accountHolderInfo,
                bankName = account.bankName,
                isDefault = account.isDefault,
                isVerified = account.isVerified,
                memberEntity = findMember,
            )
        )

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
        if (!card.isDefault && findMember.defaultPaymentId == null) {
            card.isDefault = true
        }
        val saveCard = cardJpaRepository.save(
            CardEntity(
                cardNum = card.cardNum,
                cardPassword = card.cardPassword,
                expirationDate = card.expirationDate,
                cvc = card.cvc,
                bankName = card.bankName,
                isDefault = card.isDefault,
                isVerified = card.isVerified,
                memberEntity = findMember,
            )
        )

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

    override fun findAccount(accountId: Long): Account {
        val findAccount = accountJpaRepository.findByIdOrNull(accountId) ?: throw NullPointerException("Not Found Account")
        return Account(
            id = findAccount.id,
            memberId = findAccount.memberEntity.id!!,
            accountNum = findAccount.accountNum,
            accountPassword = findAccount.accountPassword,
            accountHolder = findAccount.accountHolder,
            accountHolderInfo = findAccount.accountHolderInfo,
            bankName = findAccount.bankName,
            isDefault = findAccount.isDefault,
            isVerified = findAccount.isVerified,
        )
    }

    override fun findCard(cardId: Long): Card {
        val findCard = cardJpaRepository.findByIdOrNull(cardId) ?: throw NullPointerException("Not Found Card")
        return Card(
            id = findCard.id,
            memberId = findCard.memberEntity.id!!,
            cardNum = findCard.cardNum,
            cardPassword = findCard.cardPassword,
            expirationDate = findCard.expirationDate,
            cvc = findCard.cvc,
            bankName = findCard.bankName,
            isDefault = findCard.isDefault,
            isVerified = findCard.isVerified,
        )
    }

    private fun findMemberEntity(memberId: Long): MemberEntity {
        return memberJpaRepository.findByIdOrNull(memberId) ?: throw NullPointerException("Not Found Member")
    }
}
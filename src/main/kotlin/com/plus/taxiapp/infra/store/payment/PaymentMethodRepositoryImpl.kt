package com.plus.taxiapp.infra.store.payment

import com.plus.taxiapp.domain.payment.PaymentMethodRepository
import com.plus.taxiapp.domain.payment.account.Account
import com.plus.taxiapp.domain.payment.card.Card
import com.plus.taxiapp.domain.payment.paymentMethod.PaymentMethod
import com.plus.taxiapp.domain.payment.paymentMethod.PaymentMethods
import com.plus.taxiapp.infra.store.member.MemberEntity
import com.plus.taxiapp.infra.store.member.MemberJpaRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class PaymentMethodRepositoryImpl(
    private val accountJpaRepository: AccountJpaRepository,
    private val cardJpaRepository: CardJpaRepository,
    private val paymentMethodJpaRepository: PaymentMethodJpaRepository,
    private val memberJpaRepository: MemberJpaRepository,
) : PaymentMethodRepository {

    @Transactional
    override fun savePaymentMethod(paymentMethod: PaymentMethod): PaymentMethod {
        val (_, memberId, paymentMethodType, account, card, isDefault) = paymentMethod
        val findMember = findMemberEntity(memberId)
        val savePaymentMethod = paymentMethodJpaRepository.save(
            PaymentMethodEntity(
                memberEntity = findMember,
                paymentMethodType = paymentMethodType,
                isDefault = isDefault,
            )
        )

        return when (paymentMethodType) {
            PaymentMethod.Type.ACCOUNT -> {
                account ?: throw NullPointerException("Not Found Account")
                savePaymentMethod.accountEntity = accountJpaRepository.save(
                    AccountEntity(
                        accountNum = account.accountNum,
                        accountPassword = account.accountPassword,
                        accountHolder = account.accountHolder,
                        accountHolderInfo = account.accountHolderInfo,
                        bankName = account.bankName,
                        isVerified = account.isVerified,
                    )
                )

                val findAccount = savePaymentMethod.accountEntity!!
                PaymentMethod(
                    memberId = savePaymentMethod.memberEntity.id!!,
                    paymentMethodType = savePaymentMethod.paymentMethodType,
                    account = Account(
                        id = findAccount.id,
                        accountNum = findAccount.accountNum,
                        accountPassword = findAccount.accountPassword,
                        accountHolder = findAccount.accountHolder,
                        accountHolderInfo = findAccount.accountHolderInfo,
                        bankName = findAccount.bankName,
                        isVerified = findAccount.isVerified,
                    ),
                    card = null,
                    isDefault = savePaymentMethod.isDefault,
                )
            }

            PaymentMethod.Type.CARD -> {
                card ?: throw NullPointerException("Not Found Card")
                savePaymentMethod.cardEntity = cardJpaRepository.save(
                    CardEntity(
                        cardNum = card.cardNum,
                        cardPassword = card.cardPassword,
                        expirationDate = card.expirationDate,
                        cvc = card.cvc,
                        bankName = card.bankName,
                        isVerified = card.isVerified,
                    )
                )

                val findCard = savePaymentMethod.cardEntity!!
                PaymentMethod(
                    memberId = savePaymentMethod.memberEntity.id!!,
                    paymentMethodType = savePaymentMethod.paymentMethodType,
                    account = null,
                    card = Card(
                        cardNum = findCard.cardNum,
                        cardPassword = findCard.cardPassword,
                        expirationDate = findCard.expirationDate,
                        cvc = findCard.cvc,
                        bankName = findCard.bankName,
                        isVerified = findCard.isVerified,
                    ),
                    isDefault = savePaymentMethod.isDefault,
                )
            }
        }
    }

    override fun findPaymentMethodsByMemberId(memberId: Long): PaymentMethods {
        val findPaymentMethods = paymentMethodJpaRepository.findAllByMemberEntityId(memberId)

        return PaymentMethods(findPaymentMethods.map { it ->
            PaymentMethod(
                id = it.id,
                memberId = it.memberEntity.id!!,
                paymentMethodType = it.paymentMethodType,
                account = it.accountEntity?.let {
                    Account(
                        it.id,
                        it.accountNum,
                        it.accountPassword,
                        it.accountHolder,
                        it.accountHolderInfo,
                        it.bankName,
                        it.isVerified,
                    )
                },
                card = it.cardEntity?.let {
                    Card(
                        it.id,
                        it.cardNum,
                        it.cardPassword,
                        it.expirationDate,
                        it.cvc,
                        it.bankName,
                        it.isVerified,
                    )
                },
                isDefault = it.isDefault,
            )
        }.toMutableList())
    }

    @Transactional
    override fun updateDefault(paymentMethod: PaymentMethod) {
        val findPaymentMethod = findPaymentMethodEntity(paymentMethod.id!!)
        findPaymentMethod.isDefault = paymentMethod.isDefault
    }

    private fun findMemberEntity(memberId: Long): MemberEntity {
        return memberJpaRepository.findByIdOrNull(memberId) ?: throw NullPointerException("Not Found Member")
    }
    private fun findPaymentMethodEntity(paymentMethod: Long): PaymentMethodEntity {
        return paymentMethodJpaRepository.findByIdOrNull(paymentMethod) ?: throw NullPointerException("Not Found PaymentMethod")
    }
}
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
    private val paymentMethodJpaRepository: PaymentMethodJpaRepository,
    private val memberJpaRepository: MemberJpaRepository,
) : PaymentMethodRepository {

    @Transactional
    override fun savePaymentMethod(paymentMethod: PaymentMethod): PaymentMethod {
        val (_, memberId) = paymentMethod
        val findMember = findMemberEntity(memberId)
        val savePaymentMethod = paymentMethodJpaRepository.save(
            PaymentMethodEntity.of(paymentMethod, findMember)
        )
        return savePaymentMethod.toDomain()
    }

    override fun findPaymentMethodsByMemberId(memberId: Long): PaymentMethods {
        val findPaymentMethods = paymentMethodJpaRepository.findAllByMemberEntityId(memberId)

        return PaymentMethods(findPaymentMethods.map {
            it.toDomain()
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
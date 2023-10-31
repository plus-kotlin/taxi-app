package com.plus.taxiapp.infra.store.payment

import org.springframework.data.jpa.repository.JpaRepository

interface PaymentMethodJpaRepository: JpaRepository<PaymentMethodEntity, Long> {
    fun findAllByMemberEntityId(memberId: Long): MutableList<PaymentMethodEntity>
}
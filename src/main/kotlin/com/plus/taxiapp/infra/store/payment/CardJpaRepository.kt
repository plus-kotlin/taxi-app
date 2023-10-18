package com.plus.taxiapp.infra.store.payment

import org.springframework.data.jpa.repository.JpaRepository

interface CardJpaRepository: JpaRepository<CardEntity, Long> {
}
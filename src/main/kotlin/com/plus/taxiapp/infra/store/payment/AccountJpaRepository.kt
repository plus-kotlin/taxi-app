package com.plus.taxiapp.infra.store.payment

import org.springframework.data.jpa.repository.JpaRepository

interface AccountJpaRepository: JpaRepository<AccountEntity, Long> {
}
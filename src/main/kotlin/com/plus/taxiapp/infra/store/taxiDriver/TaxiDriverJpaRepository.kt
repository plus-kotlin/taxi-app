package com.plus.taxiapp.infra.store.taxiDriver

import org.springframework.data.jpa.repository.JpaRepository

interface TaxiDriverJpaRepository: JpaRepository<TaxiDriverEntity, Long> {

}
package com.plus.taxiapp.infra.store.taxi

import org.springframework.data.jpa.repository.JpaRepository

interface TaxiJpaRepository: JpaRepository<TaxiEntity, Long>{
}
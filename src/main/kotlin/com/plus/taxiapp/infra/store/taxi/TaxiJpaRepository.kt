package com.plus.taxiapp.infra.store.taxi

import com.plus.taxiapp.domain.taxi.Taxi
import org.springframework.data.jpa.repository.JpaRepository

interface TaxiJpaRepository : JpaRepository<TaxiEntity, Long> {
//    fun findAllByStatus(status: Taxi.Status): List<TaxiEntity>
}
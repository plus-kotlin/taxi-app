package com.plus.taxiapp.domain.taxi

import com.plus.taxiapp.infra.store.taxi.TaxiEntity

interface TaxiRepository {
    fun saveTaxi(taxi: Taxi): Taxi
    fun startDrivingTaxi(taxiId: Long)
//    fun findAllAvailableTaxi(taxiType: String): List<Long>
}
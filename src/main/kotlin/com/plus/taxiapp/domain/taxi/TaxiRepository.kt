package com.plus.taxiapp.domain.taxi

interface TaxiRepository {
    fun saveTaxi(taxi: Taxi): Taxi
}
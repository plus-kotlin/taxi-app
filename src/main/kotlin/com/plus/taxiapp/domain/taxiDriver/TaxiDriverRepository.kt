package com.plus.taxiapp.domain.taxiDriver

interface TaxiDriverRepository {
    fun saveTaxiInfo(taxi: TaxiDriver): TaxiDriver
}
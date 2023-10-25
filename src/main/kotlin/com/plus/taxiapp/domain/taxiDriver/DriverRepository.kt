package com.plus.taxiapp.domain.taxiDriver

interface DriverRepository {
    fun saveTaxiInfo(taxi: Driver): Driver
}
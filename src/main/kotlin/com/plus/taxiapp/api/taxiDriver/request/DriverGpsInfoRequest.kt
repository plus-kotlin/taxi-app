package com.plus.taxiapp.api.taxiDriver.request

data class DriverGpsInfoRequest(
    val memberId: Long,
    val originX: Double,
    val originY: Double,
)
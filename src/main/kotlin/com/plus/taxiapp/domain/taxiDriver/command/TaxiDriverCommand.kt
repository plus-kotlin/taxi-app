package com.plus.taxiapp.domain.taxiDriver.command

import com.plus.taxiapp.domain.taxiDriver.TaxiDriverType

class TaxiDriverCommand {
    data class Register(
        val memberId: Long,
        val driverId: String,
        val taxiNumber: String,
        val taxiType: TaxiDriverType,
        val taxiModel: String,
    )
}
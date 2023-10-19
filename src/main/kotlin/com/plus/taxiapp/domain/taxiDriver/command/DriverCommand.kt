package com.plus.taxiapp.domain.taxiDriver.command

import com.plus.taxiapp.domain.taxiDriver.DriverType

class DriverCommand {
    data class Register(
        val driverId: String,
        val taxiNumber: String,
        val taxiType: DriverType,
        val taxiModel: String,
    ) {
        companion object {
            fun of(
                taxiNumber: String,
                driverId: String,
                taxiType: DriverType,
                taxiModel: String,
            ): Register {
                return Register(
                    driverId = driverId,
                    taxiNumber = taxiNumber,
                    taxiType = taxiType,
                    taxiModel = taxiModel,
                )
            }
        }
    }
}
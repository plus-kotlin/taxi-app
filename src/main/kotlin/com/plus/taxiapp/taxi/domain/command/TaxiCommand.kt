package com.plus.taxiapp.taxi.domain.command

import com.plus.taxiapp.taxi.domain.TaxiType

class TaxiCommand {
    data class Register(
        val taxiNumber: String,
        val driverId: String,
        val taxiType: TaxiType,
        val taxiModel: String,
    ) {
        companion object {
            fun of(
                taxiNumber: String,
                driverId: String,
                taxiType: TaxiType,
                taxiModel: String,
            ): Register {
                return Register(
                    taxiNumber = taxiNumber,
                    driverId = driverId,
                    taxiType = taxiType,
                    taxiModel = taxiModel,
                )
            }
        }
    }
}
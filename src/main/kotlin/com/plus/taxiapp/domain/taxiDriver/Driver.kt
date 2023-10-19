package com.plus.taxiapp.domain.taxiDriver

import com.plus.taxiapp.domain.taxiDriver.command.DriverCommand

data class Driver (
    val driverId: String,
    val taxiNumber: String,
    val taxiType: DriverType,
    val taxiModel: String,
) {
    companion object {
        fun create(command: DriverCommand.Register): Driver {
            return Driver(
                driverId = command.driverId,
                taxiNumber = command.taxiNumber,
                taxiType = command.taxiType,
                taxiModel = command.taxiModel
            )
        }
    }
}
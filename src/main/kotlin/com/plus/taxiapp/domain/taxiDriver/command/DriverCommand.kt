package com.plus.taxiapp.domain.taxiDriver.command

import com.plus.taxiapp.domain.taxiDriver.DriverType
import com.plus.taxiapp.domain.taxiDriver.command.policy.DriverInfoCheckHelper

class DriverCommand {
    data class Register(
        val driverId: String,
        val taxiNumber: String,
        val taxiType: DriverType,
        val taxiModel: String,
    ) {
        init {
            DriverInfoCheckHelper.checkTaxiDetailsAreFilled(taxiNumber, taxiModel)
            DriverInfoCheckHelper.checkTaxiNumberFollowsFormat(taxiNumber)
        }
    }
}
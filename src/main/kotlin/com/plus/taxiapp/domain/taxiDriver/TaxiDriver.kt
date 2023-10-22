package com.plus.taxiapp.domain.taxiDriver

import com.plus.taxiapp.domain.taxiDriver.policy.TaxiDriverInfoCheckHelper

data class TaxiDriver (
    val driverId: String,
    val taxiNumber: String,
    val taxiType: TaxiDriverType,
    val taxiModel: String,
) {
    init {
        TaxiDriverInfoCheckHelper.checkTaxiDetailsAreFilled(taxiNumber, taxiModel)
        TaxiDriverInfoCheckHelper.checkTaxiNumberFollowsFormat(taxiNumber)
    }
}
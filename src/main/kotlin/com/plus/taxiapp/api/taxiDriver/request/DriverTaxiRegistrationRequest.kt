package com.plus.taxiapp.api.taxiDriver.request

import com.plus.taxiapp.api.taxiDriver.request.policy.RequestCheckHelper
import com.plus.taxiapp.domain.taxiDriver.DriverType


data class DriverTaxiRegistrationRequest (
    val taxiNumber: String,
    val taxiType: DriverType,
    val taxiModel: String,
){
    fun hasNoProblem(){
        RequestCheckHelper.sayTaxiNumberIsPerfect(this.taxiNumber)
    }
}
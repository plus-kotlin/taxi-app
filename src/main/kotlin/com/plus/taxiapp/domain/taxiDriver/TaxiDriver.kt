package com.plus.taxiapp.domain.taxiDriver

import com.plus.taxiapp.domain.taxiDriver.policy.TaxiDriverInfoCheckHelper
import lombok.Getter

@Getter
data class TaxiDriver(
    val driverId: String,
    val taxiNumber: String,
    val taxiType: TaxiDriverType,
    val taxiModel: String,
    var taxiStatus: TaxiStatus? = TaxiStatus.NOTDRIVING,
) {
    init {
        TaxiDriverInfoCheckHelper.checkTaxiDetailsAreFilled(taxiNumber, taxiModel)
        TaxiDriverInfoCheckHelper.checkTaxiNumberFollowsFormat(taxiNumber)
    }
}

enum class TaxiStatus {
    NOTDRIVING, // 호출 대기중이 아닌 상태
    WAITING, // 호출 대기중인 상태
    DRIVING, // 호출을 받아 운행중인 상태
    COMPLETED // 호출을 받아 운행을 끝마친 상태 -> WAITING 상태로
}

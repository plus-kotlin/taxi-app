package com.plus.taxiapp.api.taxiDriver.request

import com.plus.taxiapp.api.taxiDriver.request.policy.RequestCheckHelper
import com.plus.taxiapp.domain.taxiDriver.TaxiDriverType

/**
 * 기사의 택시 정보 등록 API Request
 * taxiNumber : String - 택시 번호판 번호 (e.g., "서울 28바 2311").
 * taxiType   : TaxiType - 택시 크기 타입 (e.g., 소형, 중형, 대형).
 * taxiModel  : String - 택시 차량 모델 (e.g., "미니 쿠퍼", "아반떼").
 *
 * @author Choichanhyeok
 */
data class DriverTaxiRegistrationRequest(
    val memberId: Long,
    val taxiNumber: String,
    val taxiType: TaxiDriverType,
    val taxiModel: String,
) {
    fun hasNoProblem() {
        RequestCheckHelper.sayTaxiNumberIsPerfect(this.taxiNumber)
    }
}
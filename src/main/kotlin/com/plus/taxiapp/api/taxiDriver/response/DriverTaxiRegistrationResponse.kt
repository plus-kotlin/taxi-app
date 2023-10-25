package com.plus.taxiapp.api.taxiDriver.response

import com.plus.taxiapp.domain.taxiDriver.TaxiDriver
import com.plus.taxiapp.domain.taxiDriver.TaxiDriverType

/**
 * 기사의 택시 정보 등록 API Response
 * driverId   : String - 택시기사 id (e.g., "0x0000").
 * taxiNumber : String - 택시 번호판 번호 (e.g., "서울 28바 2311").
 * taxiType   : TaxiType - 택시 크기 타입 (e.g., 소형, 중형, 대형).
 * taxiModel  : String - 택시 차량 모델 (e.g., "미니 쿠퍼", "아반떼").
 *
 * @author Choichanhyeok
 */
data class DriverTaxiRegistrationResponse (
    val driverId: String,
    val taxiNumber: String,
    val taxiType: TaxiDriverType,
    val taxiModel: String,
){
    companion object {
        fun create(driverInfo: TaxiDriver): DriverTaxiRegistrationResponse {
            return DriverTaxiRegistrationResponse(
                driverInfo.driverId,
                driverInfo.taxiNumber,
                driverInfo.taxiType,
                driverInfo.taxiModel
            )
        }
    }
}
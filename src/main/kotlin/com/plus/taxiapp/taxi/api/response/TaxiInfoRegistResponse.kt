package com.plus.taxiapp.taxi.api.response

import com.plus.taxiapp.taxi.domain.TaxiType
import com.plus.taxiapp.taxi.domain.entity.TaxiInfo


/**
 * 택시 정보 등록 API Response
 * taxiNumber : String - 택시 번호판 번호 (e.g., "서울 28바 2311").
 * driverId   : String - 택시기사 id (e.g., "0x0000").
 * taxiType   : TaxiType - 택시 크기 타입 (e.g., 소형, 중형, 대형).
 * taxiModel  : String - 택시 차량 모델 (e.g., "미니 쿠퍼", "아반떼").
 *
 * @author Choichanhyeok
 */
class TaxiInfoRegistResponse (
    val taxiNumber: String,
    val driverId: String,
    val taxiType: TaxiType,
    val taxiModel: String,
){
    companion object {
        fun create(taxiInfo: TaxiInfo): TaxiInfoRegistResponse {
            return TaxiInfoRegistResponse(
                taxiInfo.taxiNumber,
                taxiInfo.driverId,
                taxiInfo.taxiType,
                taxiInfo.taxiModel
            )
        }
    }
}

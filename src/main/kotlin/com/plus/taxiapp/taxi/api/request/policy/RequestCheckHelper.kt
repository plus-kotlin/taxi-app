package com.plus.taxiapp.taxi.api.request.policy

class RequestCheckHelper private constructor() {
    companion object {
        fun sayTaxiNumberIsPerfect(taxiNumber: String) {
            if (taxiNumber.isBlank()) {
                throw IllegalArgumentException("택시 번호는 필수입니다.")
            }
        }
        fun sayDriverIdIsPerfect(driverId: String) {
            if (driverId.isBlank()) {
                throw IllegalArgumentException("기사님 ID는 필수입니다.")
            }
        }
    }
}

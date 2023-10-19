package com.plus.taxiapp.api.taxiDriver.request.policy

class RequestCheckHelper private constructor() {
    companion object {
        fun sayTaxiNumberIsPerfect(taxiNumber: String) {
            if (taxiNumber.isBlank()) {
                throw IllegalArgumentException("택시 번호는 필수입니다.")
            }
        }
    }
}

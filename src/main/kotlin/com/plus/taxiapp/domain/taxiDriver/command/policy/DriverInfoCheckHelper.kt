package com.plus.taxiapp.domain.taxiDriver.command.policy


class DriverInfoCheckHelper {
    companion object {
        fun checkTaxiDetailsAreFilled(taxiNumber: String, taxiModel: String) {
            when {
                taxiNumber.isBlank() -> throw IllegalArgumentException("Taxi Number is Required")
                taxiModel.isBlank() -> throw IllegalArgumentException("Taxi Model is Required")
            }
        }

        fun checkTaxiNumberFollowsFormat(taxiNumber: String) {
            val TaxiNumberPattern = """^[가-힣]{2}\s\d{2}[가-힣]{1}\s\d{4}$""".toRegex()
            when {
                !TaxiNumberPattern.matches(taxiNumber) -> throw IllegalArgumentException("Taxi Number is not in the right format")
            }
        }
    }
}
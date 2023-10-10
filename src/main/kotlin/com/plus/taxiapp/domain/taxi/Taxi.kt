package com.plus.taxiapp.domain.taxi

data class Taxi(
    val id: Long?
) {
    enum class Status {
        DRIVING
    }
}
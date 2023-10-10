package com.plus.taxiapp.domain.taxi.command

class TaxiCommand {
    data class Register(
        val id: Long,
    )

    data class Update(
        val id: Long,
    )
}
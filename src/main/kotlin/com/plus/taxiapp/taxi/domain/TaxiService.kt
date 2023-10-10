package com.plus.taxiapp.taxi.domain

import com.plus.taxiapp.taxi.domain.command.TaxiCommand
import com.plus.taxiapp.taxi.domain.entity.TaxiInfo

interface TaxiService {
    fun driverRegister(register: TaxiCommand.Register): TaxiInfo
}
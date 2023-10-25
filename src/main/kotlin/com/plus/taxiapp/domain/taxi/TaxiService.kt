package com.plus.taxiapp.domain.taxi

import com.plus.taxiapp.domain.taxi.command.TaxiCommand
import org.springframework.stereotype.Service

@Service
class TaxiService(
    private val taxiRepository: TaxiRepository,
) {
    fun registerTaxiInfo(register: TaxiCommand.Register) {
        val taxi = taxiRepository.saveTaxi(Taxi(10))
    }
}
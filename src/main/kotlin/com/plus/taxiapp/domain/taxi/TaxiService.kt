package com.plus.taxiapp.domain.taxi

import com.plus.taxiapp.domain.taxi.command.TaxiCommand
import com.plus.taxiapp.infra.store.taxi.TaxiEntity
import org.springframework.stereotype.Service

@Service
class TaxiService(
    private val taxiRepository: TaxiRepository,
) {
    fun registerTaxiInfo(register: TaxiCommand.Register) {
        val taxi = taxiRepository.saveTaxi(Taxi(10))
    }

    fun startDrivingTaxi(taxiId: Long) {
        taxiRepository.startDrivingTaxi(taxiId)
    }

//    fun findAllAvailableTaxi(taxiType: String): List<Long> {
//        return taxiRepository.findAllAvailableTaxi(taxiType)
//    }
}
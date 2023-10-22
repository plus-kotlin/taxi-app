package com.plus.taxiapp.domain.taxiDriver

import com.plus.taxiapp.domain.taxiDriver.command.TaxiDriverCommand
import org.springframework.stereotype.Service

@Service
class TaxiDriverService(
    private val driverRepository: TaxiDriverRepository,
) {
    fun taxiInfoRegister(command: TaxiDriverCommand.Register): TaxiDriver {

        return driverRepository.saveTaxiInfo(TaxiDriver(command.driverId, command.taxiNumber, command.taxiType, command.taxiModel))
    }

}
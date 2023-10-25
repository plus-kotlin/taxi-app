package com.plus.taxiapp.domain.taxiDriver

import com.plus.taxiapp.domain.taxiDriver.command.DriverCommand
import org.springframework.stereotype.Service

@Service
class DriverService(
    private val driverRepository: DriverRepository,
) {
    fun taxiInfoRegister(command: DriverCommand.Register): Driver {

        return driverRepository.saveTaxiInfo(Driver.create(command))
    }

}
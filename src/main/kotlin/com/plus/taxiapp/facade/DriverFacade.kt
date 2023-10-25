package com.plus.taxiapp.facade

import com.plus.taxiapp.domain.taxiDriver.Driver
import com.plus.taxiapp.domain.taxiDriver.DriverService
import com.plus.taxiapp.domain.taxiDriver.command.DriverCommand
import org.springframework.stereotype.Component

@Component
class DriverFacade(
    private val driverService: DriverService,
    /* private val taxiService: TaxiService,*/ //TODO("택시 서비스 개발 완료시 주입")
) {
    fun registerDriverAndTaxi(command: DriverCommand.Register/*, taxi: Taxi*/): Driver {
        return driverService.taxiInfoRegister(command)
        // taxiService.registerTaxi(taxi)
    }
}
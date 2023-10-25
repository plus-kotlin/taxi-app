package com.plus.taxiapp.facade

import com.plus.taxiapp.domain.taxiDriver.TaxiDriver
import com.plus.taxiapp.domain.taxiDriver.TaxiDriverService
import com.plus.taxiapp.domain.taxiDriver.command.TaxiDriverCommand
import org.springframework.stereotype.Component

@Component
class TaxiDriverFacade(
    private val driverService: TaxiDriverService,
    /* private val taxiService: TaxiService,*/ //TODO("택시 서비스 개발 완료시 주입")
) {
    fun registerDriverAndTaxi(command: TaxiDriverCommand.Register/*, taxi: Taxi*/): TaxiDriver {
        return driverService.taxiInfoRegister(command)
        // taxiService.registerTaxi(taxi)
    }
}
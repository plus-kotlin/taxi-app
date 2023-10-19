package com.plus.taxiapp.domain.taxiDriver

import com.plus.taxiapp.domain.taxiDriver.command.DriverCommand
import org.springframework.stereotype.Service

@Service
class DriverService{
    fun taxiInfoRegister(command: DriverCommand.Register): Driver {

        return Driver.create(command)
    }

}

package com.plus.taxiapp.taxi.domain

import com.plus.taxiapp.taxi.domain.command.TaxiCommand
import com.plus.taxiapp.taxi.domain.entity.TaxiInfo
import lombok.NoArgsConstructor
import org.springframework.stereotype.Component

@Component
@NoArgsConstructor
class TaxiServiceImpl: TaxiService{
    override fun driverRegister(register: TaxiCommand.Register): TaxiInfo{
        TODO("Not yet implemented")
    }
}
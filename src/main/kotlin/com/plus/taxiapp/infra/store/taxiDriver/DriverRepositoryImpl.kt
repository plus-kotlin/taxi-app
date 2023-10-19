package com.plus.taxiapp.infra.store.taxiDriver

import com.plus.taxiapp.domain.taxiDriver.Driver
import com.plus.taxiapp.domain.taxiDriver.DriverRepository
import org.springframework.stereotype.Repository

@Repository
class DriverRepositoryImpl(
    private val taxiJpaRepository: DriverJpaRepository,
) : DriverRepository {
    override fun saveTaxiInfo(taxi: Driver): Driver {
        val savedTaxi = taxiJpaRepository.save(
            DriverEntity(
                taxiNumber = taxi.taxiNumber,
                driverId = taxi.driverId,
                taxiType = taxi.taxiType,
                taxiModel = taxi.taxiModel,
            )
        )
        return Driver(
            taxi.driverId,
            taxi.taxiNumber,
            taxi.taxiType,
            taxi.taxiModel,
        )
    }
}
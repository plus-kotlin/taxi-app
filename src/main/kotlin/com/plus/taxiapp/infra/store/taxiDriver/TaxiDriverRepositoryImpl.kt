package com.plus.taxiapp.infra.store.taxiDriver

import com.plus.taxiapp.domain.taxiDriver.TaxiDriver
import com.plus.taxiapp.domain.taxiDriver.TaxiDriverRepository
import org.springframework.stereotype.Repository

@Repository
class TaxiDriverRepositoryImpl(
    private val taxiJpaRepository: TaxiDriverJpaRepository,
) : TaxiDriverRepository {
    override fun saveTaxiInfo(taxi: TaxiDriver): TaxiDriver {
        val savedTaxi = taxiJpaRepository.save(
            TaxiDriverEntity(
                taxiNumber = taxi.taxiNumber,
                driverId = taxi.driverId,
                taxiType = taxi.taxiType,
                taxiModel = taxi.taxiModel,
            )
        )
        return TaxiDriver(
            taxi.driverId,
            taxi.taxiNumber,
            taxi.taxiType,
            taxi.taxiModel,
        )
    }
}
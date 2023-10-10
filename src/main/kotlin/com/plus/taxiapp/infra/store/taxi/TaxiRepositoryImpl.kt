package com.plus.taxiapp.infra.store.taxi

import com.plus.taxiapp.domain.taxi.Taxi
import com.plus.taxiapp.domain.taxi.TaxiRepository
import org.springframework.stereotype.Repository

@Repository
class TaxiRepositoryImpl(
    private val taxiJpaRepository: TaxiJpaRepository,
) : TaxiRepository {
    override fun saveTaxi(taxi: Taxi): Taxi {
        val saveTaxi = taxiJpaRepository.save(
            TaxiEntity(
                taxi.id,
            )
        )

        return Taxi(
            saveTaxi.id,
        )
    }
}
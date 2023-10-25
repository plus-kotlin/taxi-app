package com.plus.taxiapp.infra.store.taxi

import com.plus.taxiapp.domain.taxi.Taxi
import com.plus.taxiapp.domain.taxi.TaxiRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Repository
import java.util.stream.Collectors

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

    override fun startDrivingTaxi(taxiId: Long) {
        var changeTaxiStatus =
            taxiJpaRepository.findById(taxiId).orElseThrow { EntityNotFoundException("Taxi not found") }
//        changeTaxiStatus.changeTaxiStatus()
        taxiJpaRepository.save(changeTaxiStatus)
    }

//    override fun findAllAvailableTaxi(taxiType: String): List<Long> {
//        return taxiJpaRepository.findAllByStatus(Taxi.Status.DRIVING).map { it.id!! }
//    }
}
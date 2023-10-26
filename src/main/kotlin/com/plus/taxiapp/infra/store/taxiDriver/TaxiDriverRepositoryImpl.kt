package com.plus.taxiapp.infra.store.taxiDriver

import com.plus.taxiapp.domain.member.Member
import com.plus.taxiapp.domain.taxiDriver.TaxiDriver
import com.plus.taxiapp.domain.taxiDriver.TaxiDriverRepository
import com.plus.taxiapp.domain.taxiDriver.TaxiStatus
import com.plus.taxiapp.infra.store.member.MemberEntity
import org.springframework.stereotype.Repository

@Repository
class TaxiDriverRepositoryImpl(
    private val taxiJpaRepository: TaxiDriverJpaRepository,

    ) : TaxiDriverRepository {
    override fun saveTaxiInfo(taxi: TaxiDriver, member: MemberEntity): TaxiDriver {
        val savedTaxi = taxiJpaRepository.save(
            TaxiDriverEntity(
                taxiNumber = taxi.taxiNumber,
                driverId = taxi.driverId,
                taxiType = taxi.taxiType,
                taxiModel = taxi.taxiModel,
                member = member,
                taxiStatus = TaxiStatus.NOTDRIVING
            )
        )
        return TaxiDriver(
            taxi.driverId,
            taxi.taxiNumber,
            taxi.taxiType,
            taxi.taxiModel,
            taxiStatus = TaxiStatus.NOTDRIVING
        )
    }

    override fun findTaxiDriver(member: Member): TaxiDriver {
        val taxiDriver = taxiJpaRepository.findByMember(member)
        return TaxiDriver(
            taxiDriver.driverId,
            taxiDriver.taxiNumber,
            taxiDriver.taxiType,
            taxiDriver.taxiModel,
            taxiDriver.taxiStatus ?: TaxiStatus.NOTDRIVING,
        )
    }

    override fun findTaxiDriverEntity(driverId: String): TaxiDriverEntity {
        return taxiJpaRepository.findByDriverId(driverId)
    }

    override fun saveTaxiDriverEntity(driverEntity: TaxiDriverEntity) {
        taxiJpaRepository.save(driverEntity)
    }
}
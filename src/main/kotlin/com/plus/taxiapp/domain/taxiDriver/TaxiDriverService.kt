package com.plus.taxiapp.domain.taxiDriver

import com.plus.taxiapp.domain.taxiDriver.command.TaxiDriverCommand
import com.plus.taxiapp.infra.store.member.MemberEntity
import com.plus.taxiapp.infra.store.member.MemberRepositoryImpl
import org.springframework.stereotype.Service

@Service
class TaxiDriverService(
    private val driverRepository: TaxiDriverRepository,
    private val memberRepositoryImpl: MemberRepositoryImpl,
) {
    fun taxiInfoRegister(command: TaxiDriverCommand.Register): TaxiDriver {
        val member = memberRepositoryImpl.findMemberEntity(command.memberId)
        return driverRepository.saveTaxiInfo(
            TaxiDriver(
                command.driverId,
                command.taxiNumber,
                command.taxiType,
                command.taxiModel,
            ), member
        )
    }

}
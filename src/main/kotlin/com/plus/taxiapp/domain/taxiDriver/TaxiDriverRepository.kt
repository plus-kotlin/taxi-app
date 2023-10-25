package com.plus.taxiapp.domain.taxiDriver

import com.plus.taxiapp.domain.member.Member
import com.plus.taxiapp.infra.store.member.MemberEntity
import com.plus.taxiapp.infra.store.taxiDriver.TaxiDriverEntity

interface TaxiDriverRepository {
    fun saveTaxiInfo(taxi: TaxiDriver, member: MemberEntity): TaxiDriver
    fun findTaxiDriver(member: Member) : TaxiDriver
    fun findTaxiDriverEntity(driverId: String) : TaxiDriverEntity
    fun saveTaxiDriverEntity(driverEntity: TaxiDriverEntity)
}
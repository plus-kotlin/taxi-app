package com.plus.taxiapp.infra.store.taxiDriver

import com.plus.taxiapp.domain.member.Member
import org.springframework.data.jpa.repository.JpaRepository

interface TaxiDriverJpaRepository : JpaRepository<TaxiDriverEntity, Long> {
    fun findByMember(member: Member): TaxiDriverEntity

    fun findByDriverId(id:String):TaxiDriverEntity
}
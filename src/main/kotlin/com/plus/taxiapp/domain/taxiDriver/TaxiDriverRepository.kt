package com.plus.taxiapp.domain.taxiDriver

import com.plus.taxiapp.infra.store.member.MemberEntity

interface TaxiDriverRepository {
    fun saveTaxiInfo(taxi: TaxiDriver, member: MemberEntity): TaxiDriver
}
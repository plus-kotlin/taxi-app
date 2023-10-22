package com.plus.taxiapp.infra.store.taxiDriver

import com.plus.taxiapp.domain.taxiDriver.TaxiDriverType
import com.plus.taxiapp.infra.store.base.TimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class TaxiDriverEntity (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val driverId: String,
    val taxiNumber: String,
    val taxiType: TaxiDriverType,
    val taxiModel: String,
): TimeEntity()
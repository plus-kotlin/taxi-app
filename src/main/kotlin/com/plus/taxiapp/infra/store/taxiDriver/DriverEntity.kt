package com.plus.taxiapp.infra.store.taxiDriver

import com.plus.taxiapp.domain.taxiDriver.DriverType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class DriverEntity (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val driverId: String,
    val taxiNumber: String,
    val taxiType: DriverType,
    val taxiModel: String,
)
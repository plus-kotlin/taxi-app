package com.plus.taxiapp.taxi.domain.entity

import com.plus.taxiapp.taxi.domain.TaxiType

class TaxiInfo (
    val taxiNumber: String,
    val driverId: String,
    val taxiType: TaxiType,
    val taxiModel: String,
)
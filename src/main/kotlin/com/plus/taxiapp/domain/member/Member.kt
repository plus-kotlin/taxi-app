package com.plus.taxiapp.domain.member

import com.plus.taxiapp.domain.payment.paymentMethod.PaymentMethod
import com.plus.taxiapp.domain.taxiDriver.TaxiDriver

data class Member(
    var id: Long? = null,
    var name: String,
    val taxiDriver: TaxiDriver? = null,
    val paymentMethods: MutableList<PaymentMethod>? = null,
)
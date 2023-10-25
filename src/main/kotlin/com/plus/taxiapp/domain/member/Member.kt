package com.plus.taxiapp.domain.member

import com.plus.taxiapp.domain.taxi.Taxi
import com.plus.taxiapp.domain.taxiDriver.TaxiDriver

data class Member(
    var id: Long? = null,
    var name: String,
    val taxiDriver: TaxiDriver? = null,
    val defaultPaymentType: PaymentType? = null,
    val defaultPaymentId: Long? = null,
    val account: MutableList<Account>? = null,
)

enum class PaymentType {
    ACCOUNT, CARD
}
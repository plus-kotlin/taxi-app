package com.plus.taxiapp.domain

data class Member(
    var id: Long? = null,
    var name: String,
    val defaultPaymentType: PaymentType? = null,
    val defaultPaymentId: Long? = null,
    val account: MutableList<Account>? = null,
)

enum class PaymentType {
    ACCOUNT, CARD
}
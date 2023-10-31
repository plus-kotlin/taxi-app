package com.plus.taxiapp.api.payment.request

data class RegisterCardRequest(
    val memberId: Long,
    val cardNum: String,
    val cardPassword: String,
    val expirationDate: String,
    val cvc: Int,
    val bankName: String,
    val isDefault: Boolean,
)
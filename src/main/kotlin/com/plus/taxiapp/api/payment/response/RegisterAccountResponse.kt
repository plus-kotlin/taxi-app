package com.plus.taxiapp.api.payment.response

data class RegisterAccountResponse(
    val accountNum: String,
    val accountHolder: String,
    val bankName: String,
    val isDefault: Boolean,
    val isVerified: Boolean,
)
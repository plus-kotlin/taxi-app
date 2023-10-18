package com.plus.taxiapp.api.member.response

data class RegisterAccountResponse(
    val accountNum: String,
    val accountHolder: String,
    val bankName: String,
    val isDefault: Boolean,
    val isVerified: Boolean,
)
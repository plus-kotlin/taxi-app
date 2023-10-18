package com.plus.taxiapp.api.member.request

data class RegisterCardRequest(
    val memberId: Long,
    val cardNum: String,
    val cardPassword: String,
    val expirationDate: String,
    val cvc: Int,
    val bankName: String,
    val isDefault: Boolean,
)
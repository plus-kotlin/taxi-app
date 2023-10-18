package com.plus.taxiapp.domain

data class Card(
    val id: Long? = null,
    val memberId: Long,
    val cardNum: String,
    val cardPassword: String,
    val expirationDate: String,
    val cvc: Int,
    val bankName: String,
    val isDefault: Boolean,
    val isVerified: Boolean,
)
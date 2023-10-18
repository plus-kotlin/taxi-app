package com.plus.taxiapp.domain.member

data class Card(
    val id: Long? = null,
    val memberId: Long,
    val cardNum: String,
    val cardPassword: String,
    val expirationDate: String,
    val cvc: Int,
    val bankName: String,
    var isDefault: Boolean,
    val isVerified: Boolean,
)
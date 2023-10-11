package com.plus.taxiapp.domain

data class Account(
    val userId: Long,
    val accountNum: String,
    val accountHolder: String,
    val accountHolderInfo: String,
    val bankName: String,
    val isDefault: Boolean,
    val isVerified: Boolean,
)
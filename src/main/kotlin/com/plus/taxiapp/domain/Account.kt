package com.plus.taxiapp.domain

data class Account(
    val id: Long? = null,
    val memberId: Long,
    val accountNum: String,
    val accountPassword: String,
    val accountHolder: String,
    val accountHolderInfo: String,
    val bankName: String,
    val isDefault: Boolean,
    val isVerified: Boolean,
)
package com.plus.taxiapp.domain.member

data class Account(
    val id: Long? = null,
    val memberId: Long,
    val accountNum: String,
    val accountPassword: String,
    val accountHolder: String,
    val accountHolderInfo: String,
    val bankName: String,
    var isDefault: Boolean,
    val isVerified: Boolean,
)
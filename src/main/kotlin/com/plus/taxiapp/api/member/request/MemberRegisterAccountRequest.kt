package com.plus.taxiapp.api.member.request

data class MemberRegisterAccountRequest(
    val userId: Long,
    val accountNum: String,
    val accountHolder: String,
    val accountHolderInfo: String,
    val bankName: String,
    val isDefault: Boolean,
)
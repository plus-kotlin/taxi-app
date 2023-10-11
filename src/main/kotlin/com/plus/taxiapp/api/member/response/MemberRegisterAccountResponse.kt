package com.plus.taxiapp.api.member.response

data class MemberRegisterAccountResponse(
    val accountNum: String,
    val accountHolder: String,
    val bankName: String,
    val isDefault: Boolean,
    val isVerified: Boolean,
)
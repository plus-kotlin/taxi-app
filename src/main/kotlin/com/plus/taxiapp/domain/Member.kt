package com.plus.taxiapp.domain

data class Member(
    var id: Long? = null,
    var name: String,
    val account: MutableList<Account>? = null,
)
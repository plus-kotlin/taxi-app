package com.plus.taxiapp.domain.payment.account

data class Account(
    val id: Long? = null,
    val accountNum: String,
    val accountPassword: String,
    val accountHolder: String,
    val accountHolderInfo: String,
    val bankName: String,
    val isVerified: Boolean,
) {
    init {
        when {
            accountNum.isBlank() -> throw IllegalArgumentException("Account Number is Required")
            accountPassword.isBlank() -> throw IllegalArgumentException("Account Password is Required")
            accountHolder.isBlank() -> throw IllegalArgumentException("Account Holder is Required")
            accountHolderInfo.isBlank() -> throw IllegalArgumentException("Account Holder Info is Required")
            bankName.isBlank() -> throw IllegalArgumentException("Bank Name is Required")
        }
    }
}
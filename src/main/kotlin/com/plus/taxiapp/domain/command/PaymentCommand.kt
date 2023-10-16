package com.plus.taxiapp.domain.command

class PaymentCommand {
    data class RegisterAccount(
        val memberId: Long,
        val accountNum: String,
        val accountPassword: String,
        val accountHolder: String,
        val accountHolderInfo: String,
        val bankName: String,
        val isDefault: Boolean,
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
}
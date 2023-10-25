package com.plus.taxiapp.domain.member.command

class PaymentCommand {
    data class RegisterAccount(
        val memberId: Long?,
        val accountNum: String,
        val accountPassword: String,
        val accountHolder: String,
        val accountHolderInfo: String,
        val bankName: String,
        val isDefault: Boolean = false,
    ) {
        init {
            when {
                memberId == null -> throw IllegalArgumentException("Member ID is Required")
                accountNum.isBlank() -> throw IllegalArgumentException("Account Number is Required")
                accountPassword.isBlank() -> throw IllegalArgumentException("Account Password is Required")
                accountHolder.isBlank() -> throw IllegalArgumentException("Account Holder is Required")
                accountHolderInfo.isBlank() -> throw IllegalArgumentException("Account Holder Info is Required")
                bankName.isBlank() -> throw IllegalArgumentException("Bank Name is Required")
            }
        }
    }

    data class RegisterCard(
        val memberId: Long?,
        val cardNum: String,
        val cardPassword: String,
        val expirationDate: String,
        val cvc: Int?,
        val bankName: String,
        val isDefault: Boolean = false,
    ) {
        init {
            when {
                memberId == null -> throw IllegalArgumentException("Member ID is Required")
                cardNum.isBlank() -> throw IllegalArgumentException("Card Number is Required")
                cardPassword.isBlank() -> throw IllegalArgumentException("Card Password is Required")
                expirationDate.isBlank() -> throw IllegalArgumentException("Expiration Date is Required")
                cvc == null -> throw IllegalArgumentException("CVC is Required")
                bankName.isBlank() -> throw IllegalArgumentException("Bank Name is Required")
            }
        }
    }
}
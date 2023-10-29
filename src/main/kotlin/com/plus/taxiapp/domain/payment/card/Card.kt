package com.plus.taxiapp.domain.payment.card

data class Card(
    val id: Long? = null,
    val cardNum: String,
    val cardPassword: String,
    val expirationDate: String,
    val cvc: Int,
    val bankName: String,
    val isVerified: Boolean,
) {
    init {
        when {
            cardNum.isBlank() -> throw IllegalArgumentException("Card Number is Required")
            cardPassword.isBlank() -> throw IllegalArgumentException("Card Password is Required")
            expirationDate.isBlank() -> throw IllegalArgumentException("Expiration Date is Required")
            bankName.isBlank() -> throw IllegalArgumentException("Bank Name is Required")
        }
    }
}
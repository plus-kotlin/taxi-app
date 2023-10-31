package com.plus.taxiapp.domain.payment.card

import com.plus.taxiapp.domain.payment.command.PaymentMethodCommand

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

    companion object {
        fun of(
            it: PaymentMethodCommand.CardInfo,
            isVerified: Boolean,
        ): Card {
            return Card(
                cardNum = it.cardNum,
                cardPassword = it.cardPassword,
                expirationDate = it.expirationDate,
                cvc = it.cvc,
                bankName = it.bankName,
                isVerified = isVerified,
            )
        }
    }
}
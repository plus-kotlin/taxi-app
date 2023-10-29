package com.plus.taxiapp.domain.payment.command

import com.plus.taxiapp.domain.payment.paymentMethod.PaymentMethod

class PaymentMethodCommand {
    data class Add(
        val memberId: Long,
        val paymentMethodType: PaymentMethod.Type,
        val accountInfo: AccountInfo?,
        val cardInfo: CardInfo?,
        val isDefault: Boolean = false,
    )

    data class AccountInfo(
        val accountNum: String,
        val accountPassword: String,
        val accountHolder: String,
        val accountHolderInfo: String,
        val bankName: String,
    )

    data class CardInfo(
        val cardNum: String,
        val cardPassword: String,
        val expirationDate: String,
        val cvc: Int,
        val bankName: String,
    )
}
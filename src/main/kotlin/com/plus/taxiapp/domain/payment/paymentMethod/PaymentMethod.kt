package com.plus.taxiapp.domain.payment.paymentMethod

import com.plus.taxiapp.domain.payment.account.Account
import com.plus.taxiapp.domain.payment.card.Card
import com.plus.taxiapp.domain.payment.command.PaymentMethodCommand

data class PaymentMethod(
    val id: Long? = null,
    val memberId: Long,
    val paymentMethodType: Type,
    val account: Account?,
    val card: Card?,
    var isDefault: Boolean,
) {
    fun checkNeedDefaultUpdate(defaultMethod: PaymentMethod?): Boolean {
        if (!isDefault && defaultMethod == null) {
            isDefault = true
            return false
        } else if (!isDefault) {
            return false
        }

        return true
    }

    fun offDefault(): PaymentMethod {
        isDefault = false
        return this
    }

    companion object {
        fun of(
            command: PaymentMethodCommand.Add,
            isVerified: Boolean,
        ): PaymentMethod {
            return PaymentMethod(
                memberId = command.memberId,
                paymentMethodType = command.paymentMethodType,
                card = command.cardInfo?.let { Card.of(it, isVerified) },
                account = command.accountInfo?.let { Account.of(it, isVerified) },
                isDefault = command.isDefault,
            )
        }
    }

    enum class Type {
        CARD, ACCOUNT
    }
}

package com.plus.taxiapp.domain.payment.paymentMethod

import com.plus.taxiapp.domain.payment.account.Account
import com.plus.taxiapp.domain.payment.card.Card

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

    enum class Type {
        CARD, ACCOUNT
    }
}

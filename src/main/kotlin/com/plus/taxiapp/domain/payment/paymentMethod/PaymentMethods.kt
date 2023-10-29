package com.plus.taxiapp.domain.payment.paymentMethod

data class PaymentMethods(
    val paymentMethods: MutableList<PaymentMethod>,
) {
    fun getDefaultMethod(): PaymentMethod? {
        return paymentMethods.firstOrNull { it.isDefault }
    }
}
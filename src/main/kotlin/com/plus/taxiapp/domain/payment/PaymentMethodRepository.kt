package com.plus.taxiapp.domain.payment

import com.plus.taxiapp.domain.payment.paymentMethod.PaymentMethod
import com.plus.taxiapp.domain.payment.paymentMethod.PaymentMethods

interface PaymentMethodRepository {
    fun savePaymentMethod(paymentMethod: PaymentMethod): PaymentMethod
    fun findPaymentMethodsByMemberId(memberId: Long): PaymentMethods
    fun updateDefault(paymentMethod: PaymentMethod)
}
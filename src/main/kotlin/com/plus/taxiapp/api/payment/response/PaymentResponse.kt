package com.plus.taxiapp.api.payment.response

import java.time.LocalDateTime

data class PaymentResponse(
    val paymentResult: String,
    val paymentTime: LocalDateTime,
)
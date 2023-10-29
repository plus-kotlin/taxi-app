package com.plus.taxiapp.api.payment.request

data class PaymentRequest(
    val memberId: Long,
    val drivingRecordId: Long,
    val fare: Long,
)
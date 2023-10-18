package com.plus.taxiapp.api.member.request

data class PaymentRequest(
    val memberId: Long,
    val drivingRecordId: Long,
    val fare: Long,
)
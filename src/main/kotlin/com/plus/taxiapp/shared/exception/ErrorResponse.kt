package com.plus.taxiapp.shared.exception


data class ErrorResponse(
    val errorCode: String,
    val errorMessage: String,
    val details: List<String>? = null
)

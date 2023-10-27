package com.plus.taxiapp.shared.aop.advice

import com.plus.taxiapp.shared.exception.ErrorResponse
import lombok.extern.slf4j.Slf4j
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@Slf4j
@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(ex: Exception): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse("Exception000", ex.message ?: "An error occurred.")
        //log.error("Exception: ", ex)
        return ResponseEntity.internalServerError().body(errorResponse)
    }
}

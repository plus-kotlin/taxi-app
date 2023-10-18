package com.plus.taxiapp.infra.middleware.payment

import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
class PaymentMiddleWare(
    private val logger: Logger = Logger.getLogger("Payment Middle Ware"),
) {
    fun paymentByAccount(accountNum: String, accountPassword: String) {
        logger.info("Payment By Account")
    }

    fun paymentByCard(cardNum: String, cardPassword: String, cvc: Int, expiredDate: String) {
        logger.info("Payment By Card")
    }
}
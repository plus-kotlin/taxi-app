package com.plus.taxiapp.infra.client

import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
class PaymentClient(
    private val logger: Logger = Logger.getLogger("Payment Middle Ware"),
) {
    fun paymentByAccount(accountNum: String, accountPassword: String): Boolean {
        logger.info("Payment By Account")
        return true
    }

    fun paymentByCard(cardNum: String, cardPassword: String, cvc: Int, expiredDate: String): Boolean {
        logger.info("Payment By Card")
        return true
    }
}
package com.plus.taxiapp.infra.client

import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
class ValidationClient(
    private val logger: Logger = Logger.getLogger("Validation Middle Ware"),
) {
    fun validateAccount(accountNum: String, accountPassword: String): Boolean {
        logger.info("Validation Account")
        return true
    }

    fun validateCard(cardNum: String, cardPassword: String, cvc: Int, expiredDate: String): Boolean {
        logger.info("Validation Card")
        return true
    }
}
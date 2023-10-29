package com.plus.taxiapp.infra.client

import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
class ValidationClient(
    private val logger: Logger = Logger.getLogger("Validation Middle Ware"),
) {
    fun validationAccount(accountNum: String, accountPassword: String): Boolean {
        logger.info("Validation Account")
        return true
    }

    fun validationCard(cardNum: String, cardPassword: String, cvc: Int, expiredDate: String): Boolean {
        logger.info("Validation Card")
        return true
    }
}
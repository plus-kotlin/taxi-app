package com.plus.taxiapp.infra.middleware.validation

import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
class ValidationMiddleWare(
    private val logger: Logger = Logger.getLogger("Validation Middle Ware"),
) {
    fun validationAccount(accountNum: String, accountPassword: String) {
        logger.info("Validation Account")
    }

    fun validationCard(cardNum: String, cardPassword: String, cvc: Int, expiredDate: String) {
        logger.info("Validation Card")
    }
}
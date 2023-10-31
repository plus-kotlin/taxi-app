package com.plus.taxiapp.domain.Payment

import com.plus.taxiapp.domain.payment.card.Card
import com.plus.taxiapp.domain.payment.command.PaymentMethodCommand
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `Card Domain 객체 생성 확인`() {
        Assertions.assertThat(
            Card.of(
                PaymentMethodCommand.CardInfo(
                    cardNum = "4321-1234-1222",
                    cardPassword = "1234",
                    expirationDate = "2023-01-01",
                    cvc = 777,
                    bankName = "신한은행",
                ),
                isVerified = true
            )
        ).isEqualTo(ofIsVerified())
    }

    companion object {
        fun ofIsVerified(): Card {
            return Card(
                cardNum = "4321-1234-1222",
                cardPassword = "1234",
                expirationDate = "2023-01-01",
                cvc = 777,
                bankName = "신한은행",
                isVerified = true,
            )
        }
        fun ofIsNotVerified(): Card {
            return Card(
                cardNum = "4321-1234-1222",
                cardPassword = "1234",
                expirationDate = "2023-01-01",
                cvc = 777,
                bankName = "신한은행",
                isVerified = false,
            )
        }
    }
}
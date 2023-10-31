package com.plus.taxiapp.domain.Payment

import com.plus.taxiapp.domain.payment.paymentMethod.PaymentMethods
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PaymentMethodsTest {

    @Test
    fun getDefaultMethod() {
        val paymentMethods = ofAccountTypeIncludeDefault()
        assertThat(paymentMethods.getDefaultMethod()).isEqualTo(PaymentMethodTest.accountTypeIsDefault())
    }

    companion object {
        fun ofAccountType(): PaymentMethods {
            return PaymentMethods(
                mutableListOf(
                    PaymentMethodTest.accountTypeIsNotDefault(),
                    PaymentMethodTest.accountTypeIsNotDefault(),
                )
            )
        }
        fun ofAccountTypeIncludeDefault(): PaymentMethods {
            return PaymentMethods(
                mutableListOf(
                    PaymentMethodTest.accountTypeIsDefault(),
                    PaymentMethodTest.accountTypeIsNotDefault(),
                )
            )
        }
        fun ofCardType(): PaymentMethods {
            return PaymentMethods(
                mutableListOf(
                    PaymentMethodTest.cardTypeIsNotDefault(),
                    PaymentMethodTest.cardTypeIsNotDefault(),
                )
            )
        }
        fun ofCardTypeIncludeDefault(): PaymentMethods {
            return PaymentMethods(
                mutableListOf(
                    PaymentMethodTest.cardTypeIsDefault(),
                    PaymentMethodTest.cardTypeIsNotDefault(),
                )
            )
        }
    }
}
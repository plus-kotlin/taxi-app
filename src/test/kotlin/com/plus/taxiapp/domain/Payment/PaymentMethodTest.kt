package com.plus.taxiapp.domain.Payment

import com.plus.taxiapp.domain.payment.command.PaymentMethodCommand
import com.plus.taxiapp.domain.payment.paymentMethod.PaymentMethod
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PaymentMethodTest {
    @Test
    fun `PaymentMethod Domain 객체 생성 확인`() {
        assertThat(
            PaymentMethod.of(
                PaymentMethodCommand.Add(
                    memberId = 4321,
                    paymentMethodType = PaymentMethod.Type.ACCOUNT,
                    accountInfo = PaymentMethodCommand.AccountInfo(
                        accountNum = "1234-5678-9",
                        accountPassword = "1234",
                        accountHolder = "최원빈",
                        accountHolderInfo = "000101-3000000",
                        bankName = "신한은행",
                    ),
                    cardInfo = null,
                    isDefault = true,
                ),
                isVerified = true,
            )
        ).isEqualTo(accountTypeIsDefault())
    }

    @Test
    fun `checkNeedDefaultUpdate(), isDefault = true`() {
        val paymentMethod = accountTypeIsDefault()
        assertThat(paymentMethod.checkNeedDefaultUpdate(null)).isTrue()
    }

    @Test
    fun `checkNeedDefaultUpdate(), isDefault = false, defaultMethod == null`() {
        val paymentMethod = accountTypeIsNotDefault()
        assertThat(paymentMethod.checkNeedDefaultUpdate(null)).isFalse()
        assertThat(paymentMethod.isDefault).isTrue()
    }

    @Test
    fun `checkNeedDefaultUpdate(), isDefault = false, defaultMethod != null`() {
        val paymentMethod = accountTypeIsNotDefault()
        assertThat(paymentMethod.checkNeedDefaultUpdate(accountTypeIsDefault())).isFalse()
        assertThat(paymentMethod.isDefault).isFalse()
    }

    @Test
    fun offDefault() {
        val paymentMethod = accountTypeIsDefault()
        assertThat(paymentMethod.isDefault).isTrue()
        assertThat(paymentMethod.offDefault()).isEqualTo(paymentMethod)
        assertThat(paymentMethod.isDefault).isFalse()
    }

    companion object {
        fun accountTypeIsDefault(): PaymentMethod {
            return PaymentMethod(
                memberId = 4321,
                paymentMethodType = PaymentMethod.Type.ACCOUNT,
                account = AccountTest.ofIsVerified(),
                card = null,
                isDefault = true,
            )
        }
        fun accountTypeIsNotDefault(): PaymentMethod {
            return PaymentMethod(
                memberId = 4321,
                paymentMethodType = PaymentMethod.Type.ACCOUNT,
                account = AccountTest.ofIsVerified(),
                card = null,
                isDefault = false,
            )
        }
        fun accountTypeIsNotVerified(): PaymentMethod {
            return PaymentMethod(
                memberId = 4321,
                paymentMethodType = PaymentMethod.Type.ACCOUNT,
                account = AccountTest.ofIsNotVerified(),
                card = null,
                isDefault = false,
            )
        }
        fun cardTypeIsDefault(): PaymentMethod {
            return PaymentMethod(
                memberId = 4321,
                paymentMethodType = PaymentMethod.Type.CARD,
                account = null,
                card = CardTest.ofIsVerified(),
                isDefault = true,
            )
        }
        fun cardTypeIsNotDefault(): PaymentMethod {
            return PaymentMethod(
                memberId = 4321,
                paymentMethodType = PaymentMethod.Type.CARD,
                account = null,
                card = CardTest.ofIsVerified(),
                isDefault = false,
            )
        }
        fun cardTypeIsNotVerified(): PaymentMethod {
            return PaymentMethod(
                memberId = 4321,
                paymentMethodType = PaymentMethod.Type.CARD,
                account = null,
                card = CardTest.ofIsNotVerified(),
                isDefault = true,
            )
        }
    }
}
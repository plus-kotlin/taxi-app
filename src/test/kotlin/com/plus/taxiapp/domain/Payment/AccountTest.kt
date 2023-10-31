package com.plus.taxiapp.domain.Payment

import com.plus.taxiapp.domain.payment.account.Account
import com.plus.taxiapp.domain.payment.command.PaymentMethodCommand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AccountTest {
    @Test
    fun `Account Domain 객체 생성 확인`() {
        assertThat(Account.of(
            PaymentMethodCommand.AccountInfo(
                accountNum = "1234-5678-9",
                accountPassword = "1234",
                accountHolder = "최원빈",
                accountHolderInfo = "000101-3000000",
                bankName = "신한은행",
            ),
            isVerified = true
        )).isEqualTo(ofIsVerified())
    }

    companion object {
        fun ofIsVerified(): Account {
            return Account(
                accountNum = "1234-5678-9",
                accountPassword = "1234",
                accountHolder = "최원빈",
                accountHolderInfo = "000101-3000000",
                bankName = "신한은행",
                isVerified = true,
            )
        }
        fun ofIsNotVerified(): Account {
            return Account(
                accountNum = "1234-5678-9",
                accountPassword = "1234",
                accountHolder = "최원빈",
                accountHolderInfo = "000101-3000000",
                bankName = "신한은행",
                isVerified = false,
            )
        }
    }
}
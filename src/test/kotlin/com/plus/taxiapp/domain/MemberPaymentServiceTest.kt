package com.plus.taxiapp.domain

import com.plus.taxiapp.domain.command.PaymentCommand
import com.plus.taxiapp.infra.middleware.validation.ValidationMiddleWare
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any

@ExtendWith(MockitoExtension::class)
class MemberPaymentServiceTest {

    @Mock
    private lateinit var memberPaymentRepository: MemberPaymentRepository

    @Mock
    private lateinit var validationMiddleWare: ValidationMiddleWare

    @InjectMocks
    private lateinit var memberPaymentService: MemberPaymentService

    private val registerAccount = PaymentCommand.RegisterAccount(
        memberId = 4321,
        accountNum = "1234-5678-9",
        accountPassword = "1234",
        accountHolder = "최원빈",
        accountHolderInfo = "000101-3000000",
        bankName = "신한은행",
        isDefault = true,
    )
    private val account = Account(
        memberId = registerAccount.memberId,
        accountNum = registerAccount.accountNum,
        accountPassword = registerAccount.accountPassword,
        accountHolder = registerAccount.accountHolder,
        accountHolderInfo = registerAccount.accountHolderInfo,
        bankName = registerAccount.bankName,
        isDefault = registerAccount.isDefault,
        isVerified = true,
    )

    @Test
    fun `registerAccount(), 계좌 등록`() {
        given(memberPaymentRepository.saveAccount(any())).willReturn(account)
        assertThat(memberPaymentService.registerAccount(registerAccount)).isEqualTo(account)
    }

    @Test
    fun `validationAccount(), 계좌 검증 실패`() {
        given(
            validationMiddleWare.validationAccount(
                any(),
                any()
            )
        ).willThrow(IllegalArgumentException("Validation Middle Ware Exception"))

        val msg = assertThrows<IllegalArgumentException> {
            memberPaymentService.registerAccount(registerAccount)
        }.message

        assertThat(msg).isEqualTo("Validation Middle Ware Exception")
    }

    @Test
    fun `계좌 등록 요청 데이터 검증`() {
        val msg = assertThrows<IllegalArgumentException> {
            PaymentCommand.RegisterAccount(
                memberId = 4321,
                accountNum = "",
                accountPassword = "1234",
                accountHolder = "최원빈",
                accountHolderInfo = "000101-3000000",
                bankName = "신한은행",
                isDefault = true,
            )
        }.message

        assertThat(msg).isEqualTo("Account Number is Required")
    }

}
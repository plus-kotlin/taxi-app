package com.plus.taxiapp.domain.Payment

import com.plus.taxiapp.domain.member.*
import com.plus.taxiapp.domain.payment.PaymentMethodRepository
import com.plus.taxiapp.domain.payment.command.PaymentMethodCommand
import com.plus.taxiapp.domain.payment.PaymentMethodService
import com.plus.taxiapp.domain.payment.paymentMethod.PaymentMethod
import com.plus.taxiapp.infra.client.ValidationClient
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.never
import org.mockito.kotlin.verify

@ExtendWith(MockitoExtension::class)
class PaymentMethodServiceTest {

    @Mock
    private lateinit var paymentMethodRepository: PaymentMethodRepository

    @Mock
    private lateinit var validationClient: ValidationClient

    @InjectMocks
    private lateinit var paymentMethodService: PaymentMethodService

    private val registerAccountIsDefault = PaymentMethodCommand.Add(
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
    )
    private val registerAccountIsNotDefault = PaymentMethodCommand.Add(
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
        isDefault = false,
    )
    private val registerAccountIsNull = PaymentMethodCommand.Add(
        memberId = 4321,
        paymentMethodType = PaymentMethod.Type.ACCOUNT,
        accountInfo = null,
        cardInfo = null,
        isDefault = false,
    )
    private val registerCardIsDefault = PaymentMethodCommand.Add(
        memberId = 4321,
        paymentMethodType = PaymentMethod.Type.CARD,
        accountInfo = null,
        cardInfo = PaymentMethodCommand.CardInfo(
            cardNum = "4321-1234-1222",
            cardPassword = "1234",
            expirationDate = "2023-01-01",
            cvc = 777,
            bankName = "신한은행",
        ),
        isDefault = true,
    )

    @Test
    fun `addPaymentMethod(), 계좌 등록 Default 설정, 기 설정된 Default 존재`() {
        given(validationClient.validateAccount(any(), any())).willReturn(
            true
        )
        given(paymentMethodRepository.findPaymentMethodsByMemberId(any())).willReturn(
            PaymentMethodsTest.ofAccountTypeIncludeDefault()
        )
        given(paymentMethodRepository.savePaymentMethod(any())).willReturn(
            PaymentMethodTest.accountTypeIsDefault()
        )
        assertThat(paymentMethodService.addPaymentMethod(registerAccountIsDefault)).isEqualTo(
            PaymentMethodTest.accountTypeIsDefault()
        )
        verify(validationClient, times(1)).validateAccount(any(), any())
        verify(paymentMethodRepository, times(1)).updateDefault(any())
    }

    @Test
    fun `addPaymentMethod(), 계좌 등록 Default 비설정, 기 설정된 Default 없음`() {
        given(validationClient.validateAccount(any(), any())).willReturn(
            true
        )
        given(paymentMethodRepository.findPaymentMethodsByMemberId(any())).willReturn(
            PaymentMethodsTest.ofAccountType()
        )
        given(paymentMethodRepository.savePaymentMethod(any())).willReturn(
            PaymentMethodTest.accountTypeIsDefault()
        )
        assertThat(paymentMethodService.addPaymentMethod(registerAccountIsNotDefault)).isEqualTo(
            PaymentMethodTest.accountTypeIsDefault()
        )
        verify(validationClient, times(1)).validateAccount(any(), any())
        verify(paymentMethodRepository, never()).updateDefault(any())
    }

    @Test
    fun `addPaymentMethod(), accountInfo 데이터 Is Null`() {
        val msg = assertThrows<NullPointerException> {
            paymentMethodService.addPaymentMethod(registerAccountIsNull)
        }.message

        assertThat(msg).isEqualTo("Not Found Account Info")
    }

    @Test
    fun `validationAccount(), 계좌 검증 실패`() {
        given(validationClient.validateAccount(any(), any()))
            .willThrow(IllegalArgumentException("Validation Middle Ware Exception"))

        val msg = assertThrows<IllegalArgumentException> {
            paymentMethodService.addPaymentMethod(registerAccountIsDefault)
        }.message

        assertThat(msg).isEqualTo("Validation Middle Ware Exception")
    }
}
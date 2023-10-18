package com.plus.taxiapp.domain

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.given

@ExtendWith(MockitoExtension::class)
class MemberServiceTest {

    @Mock
    private lateinit var memberRepository: MemberRepository

    @InjectMocks
    private lateinit var memberService: MemberService

    private val member = Member(
        id = 1,
        name = "최원빈",
        defaultPaymentType = PaymentType.ACCOUNT,
        defaultPaymentId = 1,
    )

    @Test
    fun `updateDefaultPayment(), 기본 결제 방법 업데이트`() {
        given(memberRepository.updateDefaultPayment(1, PaymentType.ACCOUNT, 1)).willReturn(member)
        assertThat(memberService.updateDefaultPayment(1, PaymentType.ACCOUNT, 1)).isEqualTo(member)
    }

}
package com.plus.taxiapp.api.member

import com.fasterxml.jackson.databind.ObjectMapper
import com.plus.taxiapp.api.payment.request.RegisterAccountRequest
import com.plus.taxiapp.api.payment.request.RegisterCardRequest
import com.plus.taxiapp.api.payment.response.PaymentResponse
import com.plus.taxiapp.api.payment.response.RegisterAccountResponse
import com.plus.taxiapp.api.payment.response.RegisterCardResponse
import com.plus.taxiapp.domain.payment.account.Account
import com.plus.taxiapp.domain.payment.card.Card
import com.plus.taxiapp.domain.payment.command.PaymentMethodCommand
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDateTime

@WebMvcTest(MemberApi::class)
@AutoConfigureMockMvc
class MemberApiTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper
) {
    @MockBean
    private lateinit var memberFacade: MemberFacade

    private val accountRequest = RegisterAccountRequest(
        memberId = 4321,
        accountNum = "1234-5678-9",
        accountPassword = "1234",
        accountHolder = "최원빈",
        accountHolderInfo = "000101-3000000",
        bankName = "신한은행",
        isDefault = true,
    )
    private val accountResponse = RegisterAccountResponse(
        accountNum = accountRequest.accountNum,
        accountHolder = accountRequest.accountHolder,
        bankName = accountRequest.bankName,
        isDefault = accountRequest.isDefault,
        isVerified = true,
    )
    @Test
    fun `registerAccount(), 사용자는 택시 이용을 위해 계좌 정보를 저장한다`() {
        val uri = "/api/member/register/account"
        given(
            memberFacade.registerAccount(
                PaymentMethodCommand.RegisterAccount(
                    memberId = accountRequest.memberId,
                    accountNum = accountRequest.accountNum,
                    accountPassword = accountRequest.accountPassword,
                    accountHolder = accountRequest.accountHolder,
                    accountHolderInfo = accountRequest.accountHolderInfo,
                    bankName = accountRequest.bankName,
                    isDefault = accountRequest.isDefault,
                )
            )
        ).willReturn(
            Account(
                memberId = accountRequest.memberId,
                accountNum = accountRequest.accountNum,
                accountPassword = accountRequest.accountPassword,
                accountHolder = accountRequest.accountHolder,
                accountHolderInfo = accountRequest.accountHolderInfo,
                bankName = accountRequest.bankName,
                isDefault = accountRequest.isDefault,
                isVerified = true,
            )
        )

        mockMvc.perform(
            post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountRequest))
        )
            .andExpectAll(
                status().isOk,
                content()
                    .json(objectMapper.writeValueAsString(accountResponse)),
            )
            .andDo(print())
    }

    private val cardRequest = RegisterCardRequest(
        memberId = 4321,
        cardNum = "4321-1234-1222",
        cardPassword = "1234",
        expirationDate = "2023-01-01",
        cvc = 777,
        bankName = "신한은행",
        isDefault = true,
    )
    private val cardResponse = RegisterCardResponse(
        cardNum = cardRequest.cardNum,
        expirationDate = cardRequest.expirationDate,
        bankName = cardRequest.bankName,
        isDefault = cardRequest.isDefault,
        isVerified = true,
    )
    @Test
    fun `registerCard(), 사용자는 택시 이용을 위해 카드 정보를 저장한다`() {
        val uri = "/api/member/register/card"
        given(
            memberFacade.registerCard(
                PaymentMethodCommand.RegisterCard(
                    memberId = cardRequest.memberId,
                    cardNum = cardRequest.cardNum,
                    cardPassword = cardRequest.cardPassword,
                    expirationDate = cardRequest.expirationDate,
                    cvc = cardRequest.cvc,
                    bankName = cardRequest.bankName,
                    isDefault = cardRequest.isDefault,
                )
            )
        ).willReturn(
            Card(
                memberId = cardRequest.memberId,
                cardNum = cardRequest.cardNum,
                cardPassword = cardRequest.cardPassword,
                expirationDate = cardRequest.expirationDate,
                cvc = cardRequest.cvc,
                bankName = cardRequest.bankName,
                isDefault = cardRequest.isDefault,
                isVerified = true,
            )
        )

        mockMvc.perform(
            post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cardRequest))
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpectAll(
                status().isOk,
                content()
                    .json(objectMapper.writeValueAsString(cardResponse)),
            )
            .andDo(print())
    }

    private val paymentResponse = PaymentResponse(
        paymentResult = "Success",
        paymentTime = LocalDateTime.now()
    )
    @Test
    fun `paymentFare(), 기등록된 결제 정보를 토대로 결제를 진행한다`() {
        val uri = "/api/member/1/payment/1/fare/30000"
        given(
            memberFacade.paymentFare(1,1,30000.0)
        ).willReturn(
            paymentResponse.paymentTime
        )

        mockMvc.perform(
            get(uri)
        )
            .andExpectAll(
                status().isOk,
                content()
                    .json(objectMapper.writeValueAsString(paymentResponse)),
            )
            .andDo(print())
    }
}
package com.plus.taxiapp.api.member

import com.fasterxml.jackson.databind.ObjectMapper
import com.plus.taxiapp.api.member.request.RegisterAccountRequest
import com.plus.taxiapp.api.member.response.RegisterAccountResponse
import com.plus.taxiapp.domain.Account
import com.plus.taxiapp.domain.PaymentService
import com.plus.taxiapp.domain.command.PaymentCommand
import com.plus.taxiapp.facade.MemberFacade
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(MemberApi::class)
@AutoConfigureMockMvc
class MemberApiTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper
){
    @MockBean
    private lateinit var memberFacade: MemberFacade

    private val request = RegisterAccountRequest(
        memberId = 4321,
        accountPassword = "1234",
        accountNum = "1234-5678-9",
        accountHolder = "최원빈",
        accountHolderInfo = "000101-3000000",
        bankName = "신한은행",
        isDefault = true,
    )
    private val response = RegisterAccountResponse(
        accountNum = request.accountNum,
        accountHolder = request.accountHolder,
        bankName = request.bankName,
        isDefault = request.isDefault,
        isVerified = true,
    )

    @Test
    fun `registerAccount(), 사용자는 택시 이용을 위해 계좌 정보를 저장한다`() {
        val uri = "/api/member/register/account"
        given(
            memberFacade.registerAccount(
                PaymentCommand.RegisterAccount(
                    memberId = request.memberId,
                    accountNum = request.accountNum,
                    accountPassword = request.accountPassword,
                    accountHolder = request.accountHolder,
                    accountHolderInfo = request.accountHolderInfo,
                    bankName = request.bankName,
                    isDefault = request.isDefault,
                )
            )
        ).willReturn(Account(
            memberId = request.memberId,
            accountNum = request.accountNum,
            accountPassword = request.accountPassword,
            accountHolder = request.accountHolder,
            accountHolderInfo = request.accountHolderInfo,
            bankName = request.bankName,
            isDefault = request.isDefault,
            isVerified = true,
        ))

        mockMvc.perform(
            post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpectAll(
                status().isOk,
                content()
                    .json(objectMapper.writeValueAsString(response)),
            )
            .andDo(print())
    }
}
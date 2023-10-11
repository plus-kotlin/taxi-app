package com.plus.taxiapp.api.member

import com.fasterxml.jackson.databind.ObjectMapper
import com.plus.taxiapp.api.member.request.MemberRegisterAccountRequest
import com.plus.taxiapp.api.member.response.MemberRegisterAccountResponse
import com.plus.taxiapp.domain.Account
import com.plus.taxiapp.domain.MemberPaymentService
import com.plus.taxiapp.domain.command.PaymentCommand
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.mock
import org.mockito.Mock
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest
@AutoConfigureMockMvc
class PaymentApiTest{

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var memberPaymentService: MemberPaymentService

    @Test
    fun `registerAccount(), 사용자는 택시 이용을 위해 계좌 정보를 저장한다`() {
        val uri = "/api/member/register/account"
        val request = MemberRegisterAccountRequest(
            userId = 4321,
            accountNum = "1234-5678-9",
            accountHolder = "최원빈",
            accountHolderInfo = "000101-3000000",
            bankName = "신한은행",
            isDefault = true,
        )
        val response = MemberRegisterAccountResponse(
            accountNum = request.accountNum,
            accountHolder = request.accountHolder,
            bankName = request.bankName,
            isDefault = request.isDefault,
            isVerified = true,
        )
        given(
            memberPaymentService.registerAccount(
                PaymentCommand.RegisterAccount(
                    userId = request.userId,
                    accountNum = request.accountNum,
                    accountHolder = request.accountHolder,
                    accountHolderInfo = request.accountHolderInfo,
                    bankName = request.bankName,
                    isDefault = request.isDefault,
                )
            )
        ).willReturn(Account(
            userId = request.userId,
            accountNum = request.accountNum,
            accountHolder = request.accountHolder,
            accountHolderInfo = request.accountHolderInfo,
            bankName = request.bankName,
            isDefault = request.isDefault,
            isVerified = true,
        ))

        mockMvc.perform(
            MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpectAll(
                MockMvcResultMatchers.status().isOk,
                MockMvcResultMatchers.content()
                    .json(objectMapper.writeValueAsString(response)),
            )
            .andDo(MockMvcResultHandlers.print())
    }
}
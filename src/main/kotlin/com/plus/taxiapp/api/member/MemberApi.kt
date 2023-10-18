package com.plus.taxiapp.api.member

import com.plus.taxiapp.api.member.request.RegisterAccountRequest
import com.plus.taxiapp.api.member.response.RegisterAccountResponse
import com.plus.taxiapp.domain.PaymentService
import com.plus.taxiapp.domain.command.PaymentCommand
import com.plus.taxiapp.facade.MemberFacade
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/member/")
class MemberApi(
    private val memberFacade: MemberFacade,
) {
    @PostMapping("/register/account")
    fun registerAccount(
        @RequestBody request: RegisterAccountRequest,
    ): RegisterAccountResponse {
        val response = memberFacade.registerAccount(PaymentCommand.RegisterAccount(
            memberId = request.memberId,
            accountNum = request.accountNum,
            accountPassword = request.accountPassword,
            accountHolder = request.accountHolder,
            accountHolderInfo = request.accountHolderInfo,
            bankName = request.bankName,
            isDefault = request.isDefault,
        ))

        return RegisterAccountResponse(
            accountNum = response.accountNum,
            accountHolder = response.accountHolder,
            bankName = response.bankName,
            isDefault = response.isDefault,
            isVerified = response.isVerified,
        )
    }
}
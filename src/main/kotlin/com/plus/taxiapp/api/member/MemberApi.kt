package com.plus.taxiapp.api.member

import com.plus.taxiapp.api.member.request.MemberRegisterAccountRequest
import com.plus.taxiapp.api.member.response.MemberRegisterAccountResponse
import com.plus.taxiapp.domain.MemberPaymentService
import com.plus.taxiapp.domain.command.PaymentCommand
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/member/")
class MemberApi(
    private val memberRegisterPayment: MemberPaymentService,
) {
    @PostMapping("/register/account")
    fun registerAccount(
        @RequestBody request: MemberRegisterAccountRequest,
    ): MemberRegisterAccountResponse {
        val response = memberRegisterPayment.registerAccount(PaymentCommand.RegisterAccount(
            userId = request.userId,
            accountNum = request.accountNum,
            accountHolder = request.accountHolder,
            accountHolderInfo = request.accountHolderInfo,
            bankName = request.bankName,
            isDefault = request.isDefault,
        ))

        return MemberRegisterAccountResponse(
            accountNum = response.accountNum,
            accountHolder = response.accountHolder,
            bankName = response.bankName,
            isDefault = response.isDefault,
            isVerified = response.isVerified,
        )
    }
}
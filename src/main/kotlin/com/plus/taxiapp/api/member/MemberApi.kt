package com.plus.taxiapp.api.member

import com.plus.taxiapp.api.member.request.RegisterAccountRequest
import com.plus.taxiapp.api.member.request.RegisterCardRequest
import com.plus.taxiapp.api.member.response.PaymentResponse
import com.plus.taxiapp.api.member.response.RegisterAccountResponse
import com.plus.taxiapp.api.member.response.RegisterCardResponse
import com.plus.taxiapp.domain.member.command.PaymentCommand
import com.plus.taxiapp.facade.MemberFacade
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

class MemberApi {

    @RestController
    @RequestMapping("/api/member/")
    class MemberApi(
        private val memberFacade: MemberFacade,
    ) {
        @PostMapping("/register/account")
        fun registerAccount(
            @RequestBody request: RegisterAccountRequest,
        ): RegisterAccountResponse {
            val response = memberFacade.registerAccount(
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

            return RegisterAccountResponse(
                accountNum = response.accountNum,
                accountHolder = response.accountHolder,
                bankName = response.bankName,
                isDefault = response.isDefault,
                isVerified = response.isVerified,
            )
        }

        @PostMapping("/register/card")
        fun registerCard(
            @RequestBody request: RegisterCardRequest,
        ): RegisterCardResponse {
            val response = memberFacade.registerCard(
                PaymentCommand.RegisterCard(
                    memberId = request.memberId,
                    cardNum = request.cardNum,
                    cardPassword = request.cardPassword,
                    expirationDate = request.expirationDate,
                    cvc = request.cvc,
                    bankName = request.bankName,
                    isDefault = request.isDefault,
                )
            )

            return RegisterCardResponse(
                cardNum = response.cardNum,
                expirationDate = response.expirationDate,
                bankName = response.bankName,
                isDefault = response.isDefault,
                isVerified = response.isVerified,
            )
        }

        @GetMapping("/{memberId}/payment/{drivingRecordId}/fare/{fare}")
        fun paymentFare(
            @PathVariable memberId: Long,
            @PathVariable drivingRecordId: Long,
            @PathVariable fare: Double,
        ): PaymentResponse {
            val response = memberFacade.paymentFare(memberId, drivingRecordId, fare)

            return PaymentResponse(
                paymentResult = "Success",
                paymentTime = response,
            )
        }
    }
}
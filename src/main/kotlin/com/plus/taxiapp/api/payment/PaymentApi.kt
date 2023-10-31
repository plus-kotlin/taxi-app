package com.plus.taxiapp.api.payment

import com.plus.taxiapp.api.payment.request.RegisterAccountRequest
import com.plus.taxiapp.api.payment.request.RegisterCardRequest
import com.plus.taxiapp.api.payment.response.PaymentResponse
import com.plus.taxiapp.api.payment.response.RegisterAccountResponse
import com.plus.taxiapp.api.payment.response.RegisterCardResponse
import com.plus.taxiapp.domain.payment.PaymentMethodService
import com.plus.taxiapp.domain.payment.command.PaymentMethodCommand
import com.plus.taxiapp.domain.payment.paymentMethod.PaymentMethod
import com.plus.taxiapp.facade.PaymentFacade
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/payment")
class PaymentApi(
    val paymentMethodService: PaymentMethodService,
    val paymentFacade: PaymentFacade,
) {
    @PostMapping("/register/account")
    fun registerAccount(
        @RequestBody request: RegisterAccountRequest,
    ): RegisterAccountResponse {
        val response = paymentMethodService.addPaymentMethod(
            PaymentMethodCommand.Add(
                memberId = request.memberId,
                paymentMethodType = PaymentMethod.Type.ACCOUNT,
                accountInfo = PaymentMethodCommand.AccountInfo(
                    accountNum = request.accountNum,
                    accountPassword = request.accountPassword,
                    accountHolder = request.accountHolder,
                    accountHolderInfo = request.accountHolderInfo,
                    bankName = request.bankName,
                ),
                cardInfo = null,
                isDefault = request.isDefault,
            )
        )
        val account = response.account!!
        return RegisterAccountResponse(
                accountNum = account.accountNum,
                accountHolder = account.accountHolder,
                bankName = account.bankName,
                isDefault = response.isDefault,
                isVerified = account.isVerified,
            )
    }

    @PostMapping("/register/card")
    fun registerCard(
        @RequestBody request: RegisterCardRequest,
    ): RegisterCardResponse {
        val response = paymentMethodService.addPaymentMethod(
            PaymentMethodCommand.Add(
                memberId = request.memberId,
                paymentMethodType = PaymentMethod.Type.ACCOUNT,
                accountInfo = null,
                cardInfo = PaymentMethodCommand.CardInfo(
                    cardNum = request.cardNum,
                    cardPassword = request.cardPassword,
                    expirationDate = request.expirationDate,
                    cvc = request.cvc,
                    bankName = request.bankName,
                ),
                isDefault = request.isDefault,
            )
        )

        val card = response.card!!
        return RegisterCardResponse(
            cardNum = card.cardNum,
            expirationDate = card.expirationDate,
            bankName = card.bankName,
            isDefault = response.isDefault,
            isVerified = card.isVerified,
        )
    }

    @GetMapping("/{drivingRecordId}/member/{memberId}")
    fun paymentFare(
        @PathVariable drivingRecordId: Long,
        @PathVariable memberId: Long,
        @RequestParam fare: Double,
    ): PaymentResponse {
        val response = paymentFacade.paymentFare(drivingRecordId, memberId, fare)

        return PaymentResponse(
            paymentResult = "Success",
            paymentTime = response,
        )
    }
}
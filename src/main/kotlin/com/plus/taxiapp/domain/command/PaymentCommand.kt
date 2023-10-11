package com.plus.taxiapp.domain.command

class PaymentCommand {
    data class RegisterAccount(
        val userId: Long,
        val accountNum: String,
        val accountHolder: String,
        val accountHolderInfo: String,
        val bankName: String,
        val isDefault: Boolean,
    )
}
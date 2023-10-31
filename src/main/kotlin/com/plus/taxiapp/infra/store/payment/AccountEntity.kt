package com.plus.taxiapp.infra.store.payment

import com.plus.taxiapp.domain.payment.account.Account
import com.plus.taxiapp.infra.store.base.TimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "account")
class AccountEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var accountNum: String,
    var accountPassword: String,
    var accountHolder: String,
    var accountHolderInfo: String,
    var bankName: String,
    var isVerified: Boolean,
): TimeEntity() {
    fun toDomain(): Account {
        return Account(
            id = this.id,
            accountNum = this.accountNum,
            accountPassword = this.accountPassword,
            accountHolder = this.accountHolder,
            accountHolderInfo = this.accountHolderInfo,
            bankName = this.bankName,
            isVerified = this.isVerified,
        )
    }

    companion object {
        fun of(
            account: Account
        ): AccountEntity {
            return AccountEntity(
                accountNum = account.accountNum,
                accountPassword = account.accountPassword,
                accountHolder =  account.accountHolder,
                accountHolderInfo = account.accountHolderInfo,
                bankName = account.bankName,
                isVerified = account.isVerified,
            )
        }
    }
}
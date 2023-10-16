package com.plus.taxiapp.infra.store.payment

import com.plus.taxiapp.domain.Account
import com.plus.taxiapp.domain.MemberPaymentRepository
import org.springframework.stereotype.Repository

@Repository
 class MemberPaymentRepositoryImpl: MemberPaymentRepository {
    override fun saveAccount(account: Account): Account {
        TODO("Not yet implemented")
    }
}
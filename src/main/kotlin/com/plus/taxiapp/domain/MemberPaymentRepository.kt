package com.plus.taxiapp.domain

interface MemberPaymentRepository {
    fun saveAccount(account: Account): Account
}
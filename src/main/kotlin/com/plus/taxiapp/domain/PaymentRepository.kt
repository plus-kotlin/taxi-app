package com.plus.taxiapp.domain

interface PaymentRepository {
    fun saveAccount(account: Account): Account
    fun saveCard(card: Card): Card
}
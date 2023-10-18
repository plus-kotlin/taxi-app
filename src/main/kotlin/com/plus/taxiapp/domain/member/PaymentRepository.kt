package com.plus.taxiapp.domain.member

interface PaymentRepository {
    fun saveAccount(account: Account): Account
    fun saveCard(card: Card): Card
    fun findAccount(accountId: Long): Account
    fun findCard(cardId: Long): Card
}
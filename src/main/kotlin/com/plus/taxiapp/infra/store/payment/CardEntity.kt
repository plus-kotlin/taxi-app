package com.plus.taxiapp.infra.store.payment

import com.plus.taxiapp.domain.payment.card.Card
import com.plus.taxiapp.infra.store.base.TimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "card")
class CardEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var cardNum: String,
    var cardPassword: String,
    var expirationDate: String,
    var cvc: Int,
    var bankName: String,
    var isVerified: Boolean,
) : TimeEntity() {
    fun toDomain(): Card {
        return Card(
            id = this.id,
            cardNum = this.cardNum,
            cardPassword = this.cardPassword,
            expirationDate = this.expirationDate,
            cvc = this.cvc,
            bankName = this.bankName,
            isVerified = this.isVerified,
        )
    }

    companion object {
        fun of(
            card: Card
        ): CardEntity {
            return CardEntity(
                cardNum = card.cardNum,
                cardPassword = card.cardPassword,
                expirationDate = card.expirationDate,
                cvc = card.cvc,
                bankName = card.bankName,
                isVerified = card.isVerified,
            )
        }
    }
}
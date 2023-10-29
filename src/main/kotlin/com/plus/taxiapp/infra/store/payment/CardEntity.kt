package com.plus.taxiapp.infra.store.payment

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
): TimeEntity()
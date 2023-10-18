package com.plus.taxiapp.infra.store.payment

import com.plus.taxiapp.infra.store.base.TimeEntity
import com.plus.taxiapp.infra.store.member.MemberEntity
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
    var isDefault: Boolean,
    var isVerified: Boolean,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    val memberEntity: MemberEntity,
): TimeEntity()
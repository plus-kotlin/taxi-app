package com.plus.taxiapp.infra.store.payment

import com.plus.taxiapp.domain.payment.paymentMethod.PaymentMethod
import com.plus.taxiapp.infra.store.base.TimeEntity
import com.plus.taxiapp.infra.store.member.MemberEntity
import jakarta.persistence.*

@Entity
@Table(name = "payment_method")
class PaymentMethodEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    val memberEntity: MemberEntity,
    @Enumerated(EnumType.STRING)
    var paymentMethodType: PaymentMethod.Type,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = true)
    var cardEntity: CardEntity? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = true)
    var accountEntity: AccountEntity? = null,
    var isDefault: Boolean,
): TimeEntity()
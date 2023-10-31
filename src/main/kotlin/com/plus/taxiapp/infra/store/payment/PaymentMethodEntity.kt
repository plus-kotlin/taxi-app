package com.plus.taxiapp.infra.store.payment

import com.plus.taxiapp.domain.payment.account.Account
import com.plus.taxiapp.domain.payment.card.Card
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
): TimeEntity() {

    fun toDomain(): PaymentMethod {
        return PaymentMethod(
            id = this.id,
            memberId = this.memberEntity.id!!,
            paymentMethodType = this.paymentMethodType,
            account = this.accountEntity!!.toDomain(),
            card = this.cardEntity!!.toDomain(),
            isDefault = this.isDefault,
        )
    }

    companion object {
        fun of(
            paymentMethod: PaymentMethod,
            memberEntity: MemberEntity,
        ): PaymentMethodEntity {
            return PaymentMethodEntity(
                id = paymentMethod.id,
                memberEntity = memberEntity,
                paymentMethodType = paymentMethod.paymentMethodType,
                cardEntity = paymentMethod.card?.let { CardEntity.of(it) },
                accountEntity = paymentMethod.account?.let { AccountEntity.of(it) },
                isDefault = paymentMethod.isDefault,
            )
        }
    }
}
package com.plus.taxiapp.infra.store.member

import com.plus.taxiapp.domain.Account
import com.plus.taxiapp.domain.PaymentType
import com.plus.taxiapp.infra.store.payment.AccountEntity
import jakarta.persistence.*

@Entity
@Table(name = "member")
class MemberEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String,
) {
    @Enumerated(EnumType.STRING)
    var defaultPaymentType: PaymentType? = null
    var defaultPaymentId: Long? = null
    @OneToMany(mappedBy = "memberEntity")
    val accountList: MutableList<AccountEntity>? = null
}
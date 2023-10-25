package com.plus.taxiapp.infra.store.member

import com.plus.taxiapp.domain.member.PaymentType
import com.plus.taxiapp.infra.store.base.TimeEntity
import com.plus.taxiapp.infra.store.payment.AccountEntity
import com.plus.taxiapp.infra.store.taxiDriver.TaxiDriverEntity
import jakarta.persistence.*

@Entity
@Table(name = "member")
class MemberEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    var id: Long? = null,
    var name: String,
    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "taxidriver_id")
    val taxiDriverEntity: TaxiDriverEntity? = null,
) : TimeEntity() {
    @Enumerated(EnumType.STRING)
    var defaultPaymentType: PaymentType? = null
    var defaultPaymentId: Long? = null

    @OneToMany(mappedBy = "memberEntity")
    val accountList: MutableList<AccountEntity>? = null
}
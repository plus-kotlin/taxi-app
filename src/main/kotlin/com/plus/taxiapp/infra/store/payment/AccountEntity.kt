package com.plus.taxiapp.infra.store.payment

import com.plus.taxiapp.infra.store.base.TimeEntity
import com.plus.taxiapp.infra.store.member.MemberEntity
import jakarta.persistence.*

@Entity
@Table(name = "account")
class AccountEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var accountNum: String,
    var accountPassword: String,
    var accountHolder: String,
    var accountHolderInfo: String,
    var bankName: String,
    var isDefault: Boolean,
    var isVerified: Boolean,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    val memberEntity: MemberEntity,
): TimeEntity()
package com.plus.taxiapp.infra.store.member

import jakarta.persistence.*

@Entity
@Table(name = "member")
class MemberEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String,
)
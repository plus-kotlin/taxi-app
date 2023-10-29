package com.plus.taxiapp.domain.member

import com.plus.taxiapp.infra.store.member.MemberEntity

interface MemberRepository {
    fun findMember(memberId: Long): Member
    fun findMemberEntity(memberId: Long): MemberEntity
}
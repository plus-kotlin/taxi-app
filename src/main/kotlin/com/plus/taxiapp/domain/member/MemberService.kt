package com.plus.taxiapp.domain.member

import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {
    fun findMember(memberId: Long): Member {
        return memberRepository.findMember(memberId)
    }
}
package com.plus.taxiapp.infra.store.member

import com.plus.taxiapp.domain.member.Member
import com.plus.taxiapp.domain.member.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class MemberRepositoryImpl(
    private val memberJpaRepository: MemberJpaRepository,
): MemberRepository {
    @Transactional(readOnly = true)
    override fun findMember(memberId: Long): Member {
        val findMember = findMemberEntity(memberId)
        return Member(
            id = findMember.id,
            name = findMember.name,
        )
    }

    override fun findMemberEntity(memberId: Long): MemberEntity {
        return memberJpaRepository.findByIdOrNull(memberId) ?: throw NullPointerException("Not Found Member")
    }
}
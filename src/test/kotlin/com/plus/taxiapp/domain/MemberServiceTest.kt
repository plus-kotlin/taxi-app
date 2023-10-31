package com.plus.taxiapp.domain

import com.plus.taxiapp.domain.member.Member
import com.plus.taxiapp.domain.member.MemberRepository
import com.plus.taxiapp.domain.member.MemberService
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.given

@ExtendWith(MockitoExtension::class)
class MemberServiceTest {

    @Mock
    private lateinit var memberRepository: MemberRepository

    @InjectMocks
    private lateinit var memberService: MemberService

    private val member = Member(
        id = 1,
        name = "최원빈",
    )

    @Test
    fun `findMember(), Member 조회`() {
        given(memberRepository.findMember(1)).willReturn(member)
        assertThat(memberService.findMember(1)).isEqualTo(member)
    }
}
package com.plus.taxiapp.infra.store.member

import com.plus.taxiapp.domain.Member
import com.plus.taxiapp.domain.PaymentType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberRepositoryImplTest @Autowired constructor(
    private val memberJpaRepository: MemberJpaRepository,
    private val memberRepositoryImpl: MemberRepositoryImpl,
) {

    private val member = Member(
        id = 1,
        name = "최원빈",
        defaultPaymentType = PaymentType.ACCOUNT,
        defaultPaymentId = 1,
    )
    private lateinit var memberEntity: MemberEntity
    @BeforeEach
    fun setUp() {
        memberEntity = memberJpaRepository.save(
            MemberEntity(
                name = "최원빈"
            )
        )
    }

    @Test
    fun `updateDefaultPayment(), 기본 결제 방법 업데이트`() {
        val returnMember = memberRepositoryImpl.updateDefaultPayment(1, PaymentType.ACCOUNT, 1)
        assertThat(returnMember).isEqualTo(member)
    }

}
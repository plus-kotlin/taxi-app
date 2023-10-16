package com.plus.taxiapp.infra.store.payment

import com.plus.taxiapp.domain.Account
import com.plus.taxiapp.infra.store.member.MemberEntity
import com.plus.taxiapp.infra.store.member.MemberJpaRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberPaymentRepositoryImplTest @Autowired constructor(
    private val memberJpaRepository: MemberJpaRepository,
    private val memberPaymentRepositoryImpl: MemberPaymentRepositoryImpl,
) {

    private lateinit var account: Account
    @BeforeEach
    fun setUp() {
        val member = memberJpaRepository.save(
            MemberEntity(
                name = "최원빈",
            )
        )
        account = Account(
            memberId = member.id,
            accountPassword = "1234",
            accountNum = "1234-5678-9",
            accountHolder = "최원빈",
            accountHolderInfo = "000101-3000000",
            bankName = "신한은행",
            isDefault = true,
            isVerified = true,
        )
    }
    @Test
    fun `saveAccount(), 계좌 등록`() {
        val returnAccount = memberPaymentRepositoryImpl.saveAccount(account)
        assertThat(returnAccount).isEqualTo(account)
    }
}
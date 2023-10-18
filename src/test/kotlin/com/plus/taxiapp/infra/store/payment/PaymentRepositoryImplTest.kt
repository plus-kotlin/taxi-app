package com.plus.taxiapp.infra.store.payment

import com.plus.taxiapp.domain.Account
import com.plus.taxiapp.domain.Card
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
class PaymentRepositoryImplTest @Autowired constructor(
    private val memberJpaRepository: MemberJpaRepository,
    private val paymentRepositoryImpl: PaymentRepositoryImpl,
) {

    private lateinit var account: Account
    private lateinit var card: Card
    @BeforeEach
    fun setUp() {
        val member = memberJpaRepository.save(
            MemberEntity(
                name = "최원빈",
            )
        )
        account = Account(
            id = 1,
            memberId = member.id!!,
            accountNum = "1234-5678-9",
            accountPassword = "1234",
            accountHolder = "최원빈",
            accountHolderInfo = "000101-3000000",
            bankName = "신한은행",
            isDefault = true,
            isVerified = true,
        )
        card = Card(
            id = 1,
            memberId = member.id!!,
            cardNum = "4321-1234-1222",
            cardPassword = "1234",
            expirationDate = "2023-01-01",
            cvc = 777,
            bankName = "신한은행",
            isDefault = true,
            isVerified = true,
        )
    }
    @Test
    fun `saveAccount(), 계좌 등록`() {
        val returnAccount = paymentRepositoryImpl.saveAccount(account)
        assertThat(returnAccount).isEqualTo(account)
    }

    @Test
    fun `saveCard(), 카드 등록`() {
        val returnCard = paymentRepositoryImpl.saveCard(card)
        assertThat(returnCard).isEqualTo(card)
    }
}
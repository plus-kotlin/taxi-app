package com.plus.taxiapp.domain.taxiDriver

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TaxiDriverTest {

    @Nested
    inner class `택시 도메인 생성 실패 테스트` {

        @Test
        fun `택시 번호가 비어있을 때, Taxi Number is Required 예외가 발생한다`() {
            // given
            val driverId = "testDriverHyeok"
            val taxiNumber = ""
            val taxiType = TaxiDriverType.COMPACT
            val taxiModel = "에쿠스"

            // when & then
            val exception = assertThrows<IllegalArgumentException> {
                TaxiDriver(driverId, taxiNumber, taxiType, taxiModel)
            }
            assertEquals("Taxi Number is Required", exception.message)
        }

        @Test
        fun `택시 모델이 비어있을 때, Taxi Model is Required 예외가 발생한다`() {
            // given
            val driverId = "testDriverHyeok"
            val taxiNumber = "서울 28바 2311"
            val taxiType = TaxiDriverType.COMPACT
            val taxiModel = ""  // 비어있는 택시 모델

            // when & then
            val exception = assertThrows<IllegalArgumentException> {
                TaxiDriver(driverId, taxiNumber, taxiType, taxiModel)
            }
            assertEquals("Taxi Model is Required", exception.message)
        }

        @Test
        fun `택시 번호 형식이 올바르지 않을 때, Taxi Number is not in the right format 예외가 발생한다`() {
            // given
            val driverId = "testDriverHyeok"
            val taxiNumber = "잘못된 형식의 번호"
            val taxiType = TaxiDriverType.COMPACT
            val taxiModel = "에쿠스"

            // when & then
            val exception = assertThrows<IllegalArgumentException> {
                TaxiDriver(driverId, taxiNumber, taxiType, taxiModel)
            }
            assertEquals("Taxi Number is not in the right format", exception.message)
        }
    }
}

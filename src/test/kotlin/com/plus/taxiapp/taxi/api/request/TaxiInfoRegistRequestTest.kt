package com.plus.taxiapp.taxi.api.request

import com.plus.taxiapp.taxi.domain.TaxiType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


@DisplayName("택시정보 등록 요청")
class TaxiInfoRegistRequestTest{


    @Nested
    @DisplayName("택시정보 등록 실패")
    inner class TaxiInfoRegistRequestFail {
        @Test                // given                  // when                    // then
        fun `택시 번호에 space 한자리가 주어지고 hasNoProblem을 호출했을 때 "택시 번호는 필수입니다" 라는 예외가 발생한다`() {
            // Given
            val requestWithBlankTaxiNumber = TaxiInfoRegistRequest(" ", "testDriverId", TaxiType.COMPACT, "테슬라")

            // When & Then
            assertThrows<IllegalArgumentException> {
                requestWithBlankTaxiNumber.hasNoProblem()
            }
        }

        @Test
        fun `기사님 ID에 space 한자리가 주어지고 hasNoProblem을 호출했을 때 "기사님 ID는 필수입니다" 라는 예외가 발생한다`() {
            // Given
            val requestWithBlankDriverId = TaxiInfoRegistRequest("testTaxiNumber", " ", TaxiType.COMPACT, "테슬라")

            // When & Then
            assertThrows<IllegalArgumentException> {
                requestWithBlankDriverId.hasNoProblem()
            }
        }
    }

    @Nested
    @DisplayName("택시정보 등록 성공")
    inner class TaxiInfoRegistRequestSuccess{
        @Test
        fun `텍시정보 등록에 적합한 데이터가 주어지고 hasNoProblem를 호출했을 때 예외가 발생하지 않는다`() {
            // Given
            val validRequest = TaxiInfoRegistRequest("testTaxiNumber", "testDriverId", TaxiType.COMPACT, "테슬라")

            // When & Then
            assertDoesNotThrow {
                validRequest.hasNoProblem()
            }
        }
    }
}
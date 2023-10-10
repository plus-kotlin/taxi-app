package com.plus.taxiapp.taxi.api.request.policy

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("택시정보 등록 요청 정책 검토")
internal class RequestCheckHelperTest{

    @Nested
    @DisplayName("택시정보 등록 실패 정책 정상반영")
    inner class TaxiInfoRegistRequestFailPolicyCheck {
        @Test                // given                  // when                    // then
        fun `택시 번호에 space 한자리가 주어지고 sayTaxiNumberIsPerfect를 호출했을 때 "택시 번호는 필수입니다" 라는 예외가 발생한다`() {
            // Given
            val givenTaxiNumber = " "
            // When & Then
            assertThrows<IllegalArgumentException> {
                RequestCheckHelper.sayTaxiNumberIsPerfect(givenTaxiNumber)
            }
        }

        @Test
        fun `택시 번호에 여러개의 space 주어지고 sayTaxiNumberIsPerfect를 호출했을 때 "택시 번호는 필수입니다" 라는 예외가 발생한다`() {
            // Given
            val givenTaxiNumber = "     "

            // When & Then
            assertThrows<IllegalArgumentException> {
                RequestCheckHelper.sayTaxiNumberIsPerfect(givenTaxiNumber)
            }
        }

        @Test
        fun `택시 번호에 공백 문자가 주어지고 sayTaxiNumberIsPerfect를 호출했을 때 "택시 번호는 필수입니다" 라는 예외가 발생한다`() {
            // Given
            val givenTaxiNumber = ""

            // When & Then
            assertThrows<IllegalArgumentException> {
                RequestCheckHelper.sayTaxiNumberIsPerfect(givenTaxiNumber)
            }
        }

        @Test
        fun `기사님 ID에 여러개의 space 주어지고 sayDriverIdIsPerfect를 호출했을 때 "기사님 ID는 필수입니다" 라는 예외가 발생한다`() {
            // Given
            val givenDriverId = " "

            // When & Then
            assertThrows<IllegalArgumentException> {
                RequestCheckHelper.sayDriverIdIsPerfect(givenDriverId)
            }
        }

        @Test
        fun `기사님 ID에 공백 문자가 주어지고 sayDriverIdIsPerfect 호출했을 때 "기사님 ID는 필수입니다" 라는 예외가 발생한다`() {
            // Given
            val givenDriverId = "       "

            // When & Then
            assertThrows<IllegalArgumentException> {
                RequestCheckHelper.sayDriverIdIsPerfect(givenDriverId)
            }
        }

        @Test
        fun `기사님 ID에 space 한자리가 주어지고 sayDriverIdIsPerfect 호출했을 때 "기사님 ID는 필수입니다" 라는 예외가 발생한다`() {
            // Given
            val givenDriverId = ""

            // When & Then
            assertThrows<IllegalArgumentException> {
                RequestCheckHelper.sayDriverIdIsPerfect(givenDriverId)
            }
        }
    }

    @Nested
    @DisplayName("택시정보 등록 성공 정책")
    inner class TaxiInfoRegistRequestSuccess{
        @Test
        fun `텍시정보 등록에 적합한 데이터가 주어지고 sayTaxiNumberIsPerfect를 호출했을 때 예외가 발생하지 않는다`() {
            // Given
            val givenTaxiNumber = "testTaxiNumber"

            // When & Then
            assertDoesNotThrow {
                RequestCheckHelper.sayTaxiNumberIsPerfect(givenTaxiNumber)
            }
        }

        @Test
        fun `텍시정보 등록에 적합한 데이터가 주어지고 sayDriverIdIsPerfect를 호출했을 때 예외가 발생하지 않는다`() {
            // Given
            val givenDriverId = "testDriverId"

            // When & Then
            assertDoesNotThrow {
                RequestCheckHelper.sayDriverIdIsPerfect(givenDriverId)
            }
        }
    }

}
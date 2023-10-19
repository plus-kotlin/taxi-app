package com.plus.taxiapp.domain.taxiDriver.command

import com.plus.taxiapp.domain.taxiDriver.DriverType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("DriverCommand 테스트")
internal class DriverCommandTest {

    @Nested
    @DisplayName("택시 정보 Regist 성공 테스트")
    inner class ValidateTaxiInfoSuccess {

        @Test
        fun `정책에 맞는 택시 정보가 입력되면 에러가 발생하지 않는다`() {
            DriverCommand.Register(
                driverId = "testDriverHyeok",
                taxiNumber = "서울 28바 2311",
                taxiType = DriverType.COMPACT,
                taxiModel = "에쿠스"
            )
        }
    }

    @Nested
    @DisplayName("택시 정보 Regist 실패 테스트")
    inner class ValidateTaxiInfoFailure {
        @Test
        fun `잘못된 생성자 인자값이 들어가면 택시정보 등록을 할 수 없다`() {
            assertThrows<IllegalArgumentException> {
                DriverCommand.Register(
                    driverId = "testDriverHyeok",
                    taxiNumber = "",
                    taxiType = DriverType.COMPACT,
                    taxiModel = "에쿠스"
                )
            }
        }
    }
}
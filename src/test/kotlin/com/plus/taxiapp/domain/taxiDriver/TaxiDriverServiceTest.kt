package com.plus.taxiapp.domain.taxiDriver

import com.plus.taxiapp.domain.taxiDriver.command.TaxiDriverCommand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@DisplayName("택시정보 등록 테스트")
internal class TaxiDriverServiceTest {

    @Autowired
    private lateinit var driverService: TaxiDriverService

    @Nested
    @DisplayName("택시정보 등록 성공 테스트")
    inner class TaxiInfoRegistSuccess {
        @Test
        fun `정책에 맞는 입력값으로 TaxiDriver객체를 생성할 때 택시 정보가 정상적으로 등록된다`() {

            // given
            val command = TaxiDriverCommand.Register(
                driverId = "testDriverHyeok",
                taxiNumber = "서울 28바 2311",
                taxiType = TaxiDriverType.COMPACT,
                taxiModel = "에쿠스"
            )
            val taxiInfo = TaxiDriver("testDriverHyeok", "서울 28바 2311", TaxiDriverType.COMPACT, "에쿠스")


            // when
            val result = driverService.taxiInfoRegister(command)

            // then
            assertThat(result.driverId).isEqualTo(taxiInfo.driverId)
            assertThat(result.taxiNumber).isEqualTo(taxiInfo.taxiNumber)
            assertThat(result.taxiType).isEqualTo(taxiInfo.taxiType)
            assertThat(result.taxiModel).isEqualTo(taxiInfo.taxiModel)
        }
    }

    @Nested
    inner class `택시정보 등록 실패 테스트` {
        @Test
        fun `택시 번호가 비어있는 채로 TaxiDriver객체가 생성될 때 Taxi Number is Required라는 예외가 발생한다`() {
            // given
            val command = TaxiDriverCommand.Register(
                driverId = "testDriverHyeok",
                taxiNumber = "",  // 비어있는 택시 번호
                taxiType = TaxiDriverType.COMPACT,
                taxiModel = "에쿠스"
            )

            // when & then
            val exception = assertThrows<IllegalArgumentException> {
                TaxiDriver(command.driverId, command.taxiNumber, command.taxiType, command.taxiModel)
            }

            assertEquals("Taxi Number is Required", exception.message)
        }


        @Test
        fun `택시 번호 형식이 올바르지 않으면 Taxi Number is not in the right format 예외가 발생한다`() {
            // given
            val command = TaxiDriverCommand.Register(
                driverId = "testDriverHyeok",
                taxiNumber = "잘못된 형식의 번호",  // 잘못된 형식의 택시 번호
                taxiType = TaxiDriverType.COMPACT,
                taxiModel = "에쿠스"
            )

            // when & then
            val exception = assertThrows<IllegalArgumentException> {
                TaxiDriver(command.driverId, command.taxiNumber, command.taxiType, command.taxiModel)
            }

            assertEquals("Taxi Number is not in the right format", exception.message)
        }
    }
}

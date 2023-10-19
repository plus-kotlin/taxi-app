package com.plus.taxiapp.domain.taxiDriver

import com.plus.taxiapp.domain.taxiDriver.command.DriverCommand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@DisplayName("택시정보 등록 테스트")
internal class DriverServiceTest {

    @Autowired
    private lateinit var driverService: DriverService

    @Nested
    @DisplayName("택시정보 등록 성공 테스트")
    inner class TaxiInfoRegistSuccess {

        @Test
        fun `taxiInfoRegister() - 택시 정보가 올바르게 등록된다`() {
            val command = DriverCommand.Register(
                driverId = "testDriverHyeok",
                taxiNumber = "서울 28바 2311",
                taxiType = DriverType.COMPACT,
                taxiModel = "에쿠스"
            )
            val taxiInfo = Driver("testDriverHyeok", "서울 28바 2311", DriverType.COMPACT, "에쿠스")

            val result = driverService.taxiInfoRegister(command)

            assertThat(result.driverId).isEqualTo(taxiInfo.driverId)
            assertThat(result.taxiNumber).isEqualTo(taxiInfo.taxiNumber)
            assertThat(result.taxiType).isEqualTo(taxiInfo.taxiType)
            assertThat(result.taxiModel).isEqualTo(taxiInfo.taxiModel)
        }
    }
}

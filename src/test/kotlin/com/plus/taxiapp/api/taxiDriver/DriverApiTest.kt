package com.plus.taxiapp.api.taxiDriver

import com.fasterxml.jackson.databind.ObjectMapper
import com.plus.taxiapp.api.taxiDriver.request.DriverTaxiRegistrationRequest
import com.plus.taxiapp.domain.taxiDriver.TaxiDriver
import com.plus.taxiapp.domain.taxiDriver.TaxiDriverService
import com.plus.taxiapp.domain.taxiDriver.TaxiDriverType
import com.plus.taxiapp.domain.taxiDriver.command.TaxiDriverCommand
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(DriverApi::class)
@AutoConfigureMockMvc
class DriverApiTest() {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var driverService: TaxiDriverService

    @Nested
    @DisplayName("택시정보 등록 성공 테스트")
    inner class TaxiInfoRegistHttpRequestSuccessTest {
        @Test
        fun `RegisterTaxi(), 택시기사님은 영업 참여를 위해 택시 정보를 등록한다`() {
            val taxiInfoRegistRequest = DriverTaxiRegistrationRequest(
                memberId = 1L,
                taxiNumber = "서울 28바 2311",
                taxiType = TaxiDriverType.COMPACT,
                taxiModel = "에쿠스"
            )
            val registedTaxiInfo = TaxiDriver(
                "testDriverHyeok", "서울 28바 2311", TaxiDriverType.COMPACT, "에쿠스")

            given(
                driverService.taxiInfoRegister(
                    TaxiDriverCommand.Register(
                        1L,
                        "testDriverHyeok",
                        "서울 28바 2311",
                        TaxiDriverType.COMPACT,
                        "에쿠스"
                    )
                )
            ).willReturn(registedTaxiInfo)

            mockMvc.perform(
                MockMvcRequestBuilders.post("/api/drivers/testDriverHyeok/taxis")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(taxiInfoRegistRequest))
            )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.driverId").value("testDriverHyeok"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.taxiNumber").value("서울 28바 2311"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.taxiType").value("COMPACT"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.taxiModel").value("에쿠스"))
                .andDo(MockMvcResultHandlers.print())
        }
    }
}

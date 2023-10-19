package com.plus.taxiapp.api.taxiDriver

import com.fasterxml.jackson.databind.ObjectMapper
import com.plus.taxiapp.api.taxiDriver.request.DriverTaxiRegistrationRequest
import com.plus.taxiapp.domain.taxiDriver.Driver
import com.plus.taxiapp.domain.taxiDriver.DriverService
import com.plus.taxiapp.domain.taxiDriver.DriverType
import com.plus.taxiapp.domain.taxiDriver.command.DriverCommand
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
    private lateinit var driverService: DriverService

    @Test
    fun `RegisterTaxi(), 택시기사님은 영업 참여를 위해 택시 정보를 등록한다`() {
        val taxiInfoRegistRequest = DriverTaxiRegistrationRequest(taxiNumber = "서울 28바 2311", taxiType = DriverType.COMPACT, taxiModel =  "에쿠스")
        val registedTaxiInfo = Driver("testDriverHyeok", "서울 28바 2311", DriverType.COMPACT, "에쿠스")

        given(driverService.taxiInfoRegister(DriverCommand.Register("testDriverHyeok", "서울 28바 2311", DriverType.COMPACT, "에쿠스"))).willReturn(registedTaxiInfo)

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

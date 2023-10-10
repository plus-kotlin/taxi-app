package com.plus.taxiapp.taxi.api
import com.fasterxml.jackson.databind.ObjectMapper
import com.plus.taxiapp.taxi.api.request.TaxiInfoRegistRequest
import com.plus.taxiapp.taxi.domain.TaxiService
import com.plus.taxiapp.taxi.domain.TaxiType
import com.plus.taxiapp.taxi.domain.command.TaxiCommand
import com.plus.taxiapp.taxi.domain.entity.TaxiInfo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
internal class TaxiControllerTest(@Autowired val mockMvc: MockMvc, @Autowired val objectMapper: ObjectMapper) {

    @MockBean
    lateinit var taxiService: TaxiService

    @BeforeEach
    fun setUp() {
    }

    @DisplayName("신규 택시 정보 등록을 테스트한다.")
    @Test
    fun taxiInfoRegister() {
        // Given
        val taxiInfoRegistRequest = TaxiInfoRegistRequest("taxiNumber", "driverId", TaxiType.COMPACT, "model")
        val registedTaxiInfo = TaxiInfo("taxiNumber", "driverId", TaxiType.COMPACT, "model")

        Mockito.`when`(taxiService.driverRegister(
            TaxiCommand.Register.of(
            taxiInfoRegistRequest.taxiNumber,
            taxiInfoRegistRequest.driverId,
            taxiInfoRegistRequest.taxiType,
            taxiInfoRegistRequest.taxiModel
        ))).thenReturn(registedTaxiInfo)

        // When & Then
        mockMvc.perform(post("/team7/api/v1/taxi/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(taxiInfoRegistRequest)))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.taxiNumber").value("taxiNumber"))
            .andExpect(jsonPath("$.driverId").value("driverId"))
            .andExpect(jsonPath("$.taxiType").value("COMPACT"))
            .andExpect(jsonPath("$.taxiModel").value("model"))
    }
}

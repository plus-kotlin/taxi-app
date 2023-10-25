package com.plus.taxiapp.kakao

import com.fasterxml.jackson.databind.ObjectMapper
import com.plus.taxiapp.kakao.entity.Place
import com.plus.taxiapp.kakao.service.PlaceService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("장소 즐겨찾기 테스트")
class PlaceServiceTest(
    @Value("\${kakao.rest.key}") private val kakaoApiKey: String,
    @Autowired private val placeService: PlaceService,
    @Autowired val mockMvc: MockMvc,
    @Autowired val objectMapper: ObjectMapper,

    ) {

    @DisplayName("장소 즐겨찾기 테스트")
    @Test
    fun `장소 즐겨찾기 테스트`() {
        // Given

        val place = Place(
            id = 1L,
            userId = 1L,
            customPlaceName = "Home",
            placeName = "Seoul",
            xCoordinate = 37.5665,
            yCoordinate = 126.9780
        )

        // When & Then
        mockMvc.perform(
            post("/team7/api/v1/place/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(place))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.userId").value(1L))
            .andExpect(jsonPath("$.customPlaceName").value("Home"))
            .andExpect(jsonPath("$.placeName").value("Seoul"))
            .andExpect(jsonPath("$.xCoordinate").value(37.5665))
            .andExpect(jsonPath("$.yCoordinate").value(126.9780))


    }
}
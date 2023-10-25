package com.plus.taxiapp.kakao

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.web.client.RestTemplate

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("[Kakao API] 주소, 길찾기 테스트")
class CallTaxiTest(
    @Value("\${kakao.rest.key}") private val kakaoApiKey: String) {
    // 테스트 코드 개선은 기능 작성 완료 후 추후에 진행하겠습니다.
    @DisplayName("Kakao rest api 주소 검색하기")
    @Test
    fun `API 주소 검색하기`() {
        // Given
        val analuzeType = "similar"
        val target = "이의동"

        val url = "https://dapi.kakao.com/v2/local/search/address.json?analuze_type=${analuzeType}&query=$target"

        val headers = HttpHeaders()
        headers.set("Authorization", kakaoApiKey)

        val entity: HttpEntity<String> = HttpEntity(headers)

        // When
        val restTemplate = RestTemplate()
        val searchResult = restTemplate.exchange(url, HttpMethod.GET, entity, String::class.java)

        println(searchResult.body)
    }

    @DisplayName("[Kakao rest api] 좌표로 행정구역정보 받기")
    @Test
    fun `좌표로 행정구역정보 받기`() {
        // Given
        val x = 127.046372274239
        val y = 37.3036861320947

        val url = "https://dapi.kakao.com/v2/local/geo/coord2regioncode.json?x=${x}&y=${y}"

        val headers = HttpHeaders()
        headers.set("Authorization", kakaoApiKey)

        val entity: HttpEntity<String> = HttpEntity(headers)

        // When
        val restTemplate = RestTemplate()
        val searchResult = restTemplate.exchange(url, HttpMethod.GET, entity, String::class.java)

        println(searchResult.body)
    }

    @DisplayName("[Kakao rest api] 좌표로 주소 변환하기")
    @Test
    fun `좌표로 주소 변환하기`() {
        // Given
        val x = 127.046372274239 // 입력 받는 값
        val y = 37.3036861320947 // 입력 받는 값

        val url = "https://dapi.kakao.com/v2/local/geo/coord2address.json?x=${x}&y=${y}"

        val headers = HttpHeaders()
        headers.set("Authorization", kakaoApiKey)

        val entity: HttpEntity<String> = HttpEntity(headers)

        // When
        val restTemplate = RestTemplate()
        val searchResult = restTemplate.exchange(url, HttpMethod.GET, entity, String::class.java)

        println(searchResult.body)
    }

    @DisplayName("[Kakao rest api] 키워드로 장소 검색하기")
    @Test
    fun `키워드로 장소 검색하기`() {
        // Given
        val target = "연무동 244"
        val url = "https://dapi.kakao.com/v2/local/search/keyword.json?query=${target}"

        val headers = HttpHeaders()
        headers.set("Authorization", kakaoApiKey)

        val entity: HttpEntity<String> = HttpEntity(headers)

        // When
        val restTemplate = RestTemplate()
        val searchResult = restTemplate.exchange(url, HttpMethod.GET, entity, String::class.java)

        println(searchResult.body)
    }

    @DisplayName("[Kakao mobility api] 다중 출발지 길찾기")
    @Test
    fun `다중 출발지 길찾기`() {
        // Given
        val origins = listOf(
            mapOf("x" to 127.1331694942593, "y" to 37.4463137562622, "key" to "0"),
            mapOf("x" to 127.13243772760565, "y" to 37.44148514309502, "key" to "1")
        )

        val destination = mapOf("x" to 127.14816492905383, "y" to 37.4401690139602)

        val url = "https://apis-navi.kakaomobility.com/v1/origins/directions"

        val headers = HttpHeaders()
        headers.set("Authorization", kakaoApiKey)
        headers.set("Content-Type", "application/json")

        // Body 데이터 설정
        val bodyData: Map<String, Any> = mapOf(
            "origins" to origins, "destination" to destination, "radius" to 5000
            // 필요한 경우 추가 파라미터 설정 가능 (예: radius)
        )

        // HttpEntity 객체 생성 (body와 headers 포함)
        val entity: HttpEntity<Map<String, Any>> = HttpEntity(bodyData, headers)

        // When
        val restTemplate = RestTemplate()

        /* exchange 함수의 두 번째 인수는 HTTP 메서드이며,
         * 이 경우 POST나 PUT과 같은 요청 본문을 포함할 수 있는 메서드가 되어야 합니다.
         */
        val searchResult = restTemplate.exchange(url, HttpMethod.POST, entity, String::class.java)

        println(searchResult.body)
    }

    @DisplayName("[Kakao mobility api] 자동차 길찾기")
    @Test
    fun `자동차 길찾기`() { // 택시기사가 호출자에게 가는 길 or 목적지로 가는 길
        // Given
        val originX = 127.111202 // 운전기사 gps 정보
        val originY = 37.394912 // 운전기사 gps 정보

        val destinationX = 127.046372274239 // 호출자 gps 정보
        val destinationY = 37.3036861320947 // 호출자 gps 정보

        val url =
            "https://apis-navi.kakaomobility.com/v1/directions?origin=${originX},${originY}" + "&destination=${destinationX},${destinationY}"

        val headers = HttpHeaders()
        headers.set("Authorization", kakaoApiKey)
        headers.set("Content-Type", "application/json")

        val entity: HttpEntity<String> = HttpEntity(headers)

        // When
        val restTemplate = RestTemplate()
        val searchResult = restTemplate.exchange(url, HttpMethod.GET, entity, String::class.java)

        println(searchResult.body)
    }
}
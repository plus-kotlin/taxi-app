package com.plus.taxiapp

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.plus.taxiapp.response.Response
import com.plus.taxiapp.response.RouteInfo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("길찾기 서비스 테스트")
class TaxiCallServiceTest(@Value("\${kakao.rest.key}") private val kakaoApiKey: String) {
    @DisplayName("Kakao rest api 주소 검색하기")
    @Test
    fun `API 주소 검색하기 출력 `() {
        val target = "수원"
        val url = "https://dapi.kakao.com/v2/local/search/keyword.json?query=${target}"

        val headers = HttpHeaders()
        headers.set("Authorization", kakaoApiKey)

        HttpEntity<String>(headers).let { entity ->
            RestTemplate().exchange(url, HttpMethod.GET, entity, String::class.java).also { response ->
                jacksonObjectMapper().readValue<Response>(response.body!!).documents.forEach { document ->
                    println("Place Name : ${document.placeName}")
                    println("Address Name : ${document.addressName}")
                    println("Road Address Name : ${document.roadAddressName}")
                    println("X Coordinate : ${document.x}")
                    println("Y Coordinate : ${document.y}\n")
                }
            }
        }
    }

    @DisplayName("a Kakao rest api 주소 검색하기")
    @Test
    fun `API 주소 검색하기`(): Response {
        val target = "수원"
        val url = "https://dapi.kakao.com/v2/local/search/keyword.json?query=${target}"


        HttpHeaders().apply {
            this.set("Authorization", kakaoApiKey)

            HttpEntity<String>(this).let { entity ->

                RestTemplate().exchange(url, HttpMethod.GET, entity, Response::class.java).also { response ->
                    println(response.body.toString())
                    return response.body!!
                }
            }
        }
    }


    @DisplayName("자동차 길찾기")
    @Test
    fun `자동차 길찾기`() {
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

    @DisplayName("b 모빌리티 길찾기")
    @Test
    fun `b 모빌리티 길찾기`() {
        // Given
        val originX = 127.111202 // 운전기사 gps 정보
        val originY = 37.394912 // 운전기사 gps 정보

        val destinationX = 127.046372274239 // 호출자 gps 정보
        val destinationY = 37.3036861320947 // 호출자 gps 정보

        var url =
            "https://apis-navi.kakaomobility.com/v1/directions?origin=${originX},${originY}" +
                    "&destination=${destinationX},${destinationY}"

        var headers = HttpHeaders()
        headers.set("Authorization", kakaoApiKey)
        headers.set("Content-Type", "application/json")

        HttpEntity<String>(headers).let { entity ->
            RestTemplate().exchange(url, HttpMethod.GET, entity, String::class.java).also { response ->
                var node = ObjectMapper().readTree(response.body)

                 RouteInfo(
                    resultCode = node["routes"][0]["result_code"].intValue(),
                    priority = node["routes"][0]["summary"]["priority"].textValue(),
                    taxi = node["routes"][0]["summary"]["fare"]["taxi"].intValue(),
                    toll = node["routes"][0]["summary"]["fare"]["toll"].intValue(),
                    distance = node["routes"][0]["summary"]["distance"].intValue(),
                    duration = node["routes"][0]["summary"]["duration"].intValue()
                )
            }
        }
    }
}
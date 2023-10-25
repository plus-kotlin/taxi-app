package com.plus.taxiapp.api.taxiDriver.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class TaxiFareAndDistanceInfo(
    @JsonProperty("result_code")
    val taxi: Int, // 택시요금(원)

    val toll: Int, // 톨게이트 비용(원)

    val distance: Int, // 거리(미터)

    val duration: Int // 시간(초)
)
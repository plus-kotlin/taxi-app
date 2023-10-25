package com.plus.taxiapp.kakao.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Response(
    @JsonProperty("total_count") val totalCount: Int,
    val documents: List<Document>,
)

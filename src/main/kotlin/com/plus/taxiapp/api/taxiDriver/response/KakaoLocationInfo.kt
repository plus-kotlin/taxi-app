package com.plus.taxiapp.api.taxiDriver.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class KakaoLocationInfo(
    @JsonProperty("total_count") val totalCount: Int,
    val documents: List<KakaoDetailLocationInfo>,
)
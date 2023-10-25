package com.plus.taxiapp.api.taxiDriver.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class KakaoDetailLocationInfo(
    @JsonProperty("place_name") val placeName: String,
    @JsonProperty("address_name") val addressName: String,
    @JsonProperty("road_address_name") val roadAddressName: String,
    val x: String,
    val y: String,
)

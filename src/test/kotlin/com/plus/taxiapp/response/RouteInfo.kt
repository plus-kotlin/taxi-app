package com.plus.taxiapp.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class RouteInfo(
    @JsonProperty("result_code")
    val resultCode: Int,

    val priority: String,

    val taxi: Int,

    val toll: Int,

    val distance: Int,

    val duration: Int
)

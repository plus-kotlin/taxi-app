package com.plus.taxiapp.api.member.request

data class GpsInfo(
    val originX: String, // 출발지 x 좌표
    val originY: String, // 출발지 y 좌표
    val destinationX: String, // 목적지 x 좌표
    val destinationY: String, // 목적지 y 좌표
)

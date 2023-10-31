package com.plus.taxiapp.api.member

import com.plus.taxiapp.api.member.request.GpsInfo
import com.plus.taxiapp.api.taxiDriver.response.KakaoLocationInfo
import com.plus.taxiapp.api.taxiDriver.response.TaxiFareAndDistanceInfo
import com.plus.taxiapp.domain.taxiDriver.TaxiDriver
import com.plus.taxiapp.domain.taxiDriver.TaxiDriverService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/member")
class MemberApi(
    private val taxiDriverService: TaxiDriverService,
) {
    @GetMapping("/call")
    fun getLocationInfo(
        @PathVariable placeName: String,
    ): KakaoLocationInfo {
        return taxiDriverService.getLocationInfo(placeName)
    }

    @GetMapping("/call/place")
    fun getFareAndDistanceInfo(callData: GpsInfo): TaxiFareAndDistanceInfo? {
        return taxiDriverService.getFareAndDistanceInfo(callData)
    }

    @GetMapping("/call/start/{memberId}/{x}/{y}/{range}")
    fun startCallMember(
        @PathVariable memberId: Long,
        @PathVariable x: Double,
        @PathVariable y: Double,
    ): TaxiDriver {
        return taxiDriverService.startCallMember(memberId, x, y)
    }
}
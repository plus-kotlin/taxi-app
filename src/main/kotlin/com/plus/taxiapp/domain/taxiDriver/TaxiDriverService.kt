package com.plus.taxiapp.domain.taxiDriver

import com.fasterxml.jackson.databind.ObjectMapper
import com.plus.taxiapp.*
import com.plus.taxiapp.api.member.request.GpsInfo
import com.plus.taxiapp.api.taxiDriver.request.DriverGpsInfoRequest
import com.plus.taxiapp.api.taxiDriver.response.KakaoLocationInfo
import com.plus.taxiapp.api.taxiDriver.response.TaxiFareAndDistanceInfo
import com.plus.taxiapp.domain.member.MemberRepository
import com.plus.taxiapp.domain.taxiDriver.command.TaxiDriverCommand
import com.plus.taxiapp.infra.store.taxiDriver.TaxiDriverEntity
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import kotlin.random.Random

@Service
class TaxiDriverService(
    @Value("\${kakao.rest.key}") private val kakaoApiKey: String,
    private val driverRepository: TaxiDriverRepository,
    private val memberRepository: MemberRepository,
) {
    fun taxiInfoRegister(command: TaxiDriverCommand.Register): TaxiDriver {
        val member = memberRepository.findMemberEntity(command.memberId)
        return driverRepository.saveTaxiInfo(
            TaxiDriver(
                command.driverId,
                command.taxiNumber,
                command.taxiType,
                command.taxiModel,
                TaxiStatus.NOTDRIVING,
            ), member
        )
    }

    fun findNearbyTaxis(x: Double, y: Double, range: Double): List<NearbyTaxi> {
        return drivingInMemory.filter { (key, value) ->
            val dx = value.x - x
            val dy = value.y - y
            val distanceSquared = dx * dx + dy * dy

            distanceSquared <= range * range
        }.map { NearbyTaxi(it.key, it.value) }
    }

    data class NearbyTaxi(
        val id: String, val gpsInfo: TaxiGpsInfoInMemory
    )

    // 주변 택시 없을 경우 50씩 증가하며 재탐색
    fun startCallMember(memberId: Long, x: Double, y: Double): TaxiDriverEntity {
        val member = memberRepository.findMember(memberId)

        var range = 50.0
        var findNearbyTaxis = findNearbyTaxis(x, y, range)

        var executionCount = 0
        while (findNearbyTaxis.isEmpty() && executionCount < 5) {
            if (findNearbyTaxis.isNotEmpty()) break
            range += 50.0
            findNearbyTaxis = findNearbyTaxis(x, y, range)
            executionCount++
        }

        val nearbyTaxi = findNearbyTaxis[0]
        val driver = driverRepository.findTaxiDriverEntity(nearbyTaxi.id)
        this.changeDriverStatus(driver, TaxiStatus.DRIVING)
        // 이미 운행 중일 경우 조회하지 않도록 설정 추가

        return driverRepository.findTaxiDriverEntity(nearbyTaxi.id)
    }

    fun changeDriverStatus(taxiDriver: TaxiDriverEntity, status: TaxiStatus) {
        taxiDriver?.taxiStatus = status
        driverRepository.saveTaxiDriverEntity(taxiDriver)
    }

    fun changeDriverStatus(taxiDriver: TaxiDriver, status: TaxiStatus) {
        taxiDriver?.taxiStatus = status
        val driverEntity = driverRepository.findTaxiDriverEntity(
            driverId = taxiDriver.driverId
        )
        driverRepository.saveTaxiDriverEntity(driverEntity)
    }


    fun waitingCallDriver(driverGpsInfoRequest: DriverGpsInfoRequest) {
        val member = memberRepository.findMember(driverGpsInfoRequest.memberId)
        val taxiDriver = driverRepository.findTaxiDriver(member)
        this.changeDriverStatus(taxiDriver, TaxiStatus.WAITING)

        drivingInMemory.put(
            taxiDriver?.driverId!!, TaxiGpsInfoInMemory(
                driverGpsInfoRequest.originX, driverGpsInfoRequest.originY
            )
        )

        this.waitingDriver(taxiDriver.driverId)
    }

    fun waitingDriver(key: String) {
        threadPool.execute {
            while (!Thread.currentThread().isInterrupted) {
                drivingInMemory[key]?.let { taxiInMemory ->
                    synchronized(taxiInMemory) {
                        // 랜덤하지만 일정한 방향으로 더 많이 이동한다는 설정(택시 위치 이동)
                        taxiInMemory.x += Random.nextDouble(-2.0, 8.0)
                        taxiInMemory.y += Random.nextDouble(-2.0, 8.0)
                    }
                }
                Thread.sleep(1500)
            }
        }
    }

    fun removeDrivingThread(key: String) { // pool 제거(호출대기가 아닌 상태)
        threads[key]?.let { thread ->
            thread.interrupt()
            threads.remove(key)
            drivingInMemory.remove(key)
        }
    }

    fun getLocationInfo(placeName: String): KakaoLocationInfo {
        val url = "https://dapi.kakao.com/v2/local/search/keyword.json?query=${placeName}"

        HttpHeaders().apply {
            this.set("Authorization", kakaoApiKey)

            HttpEntity<String>(this).let { entity ->
                RestTemplate().exchange(url, HttpMethod.GET, entity, KakaoLocationInfo::class.java).also { response ->
                    return response.body ?: throw NullPointerException("[Exception] Kakao Api check")
                }
            }
        }
    }

    fun getFareAndDistanceInfo(gpsInfo: GpsInfo): TaxiFareAndDistanceInfo? {
        var url =
            "https://apis-navi.kakaomobility.com/v1/directions?origin=${gpsInfo.originX},${gpsInfo.originY}" + "&destination=${gpsInfo.destinationX},${gpsInfo.destinationY}"

        var headers = HttpHeaders()
        headers.set("Authorization", kakaoApiKey)
        headers.set("Content-Type", "application/json")

        var routeInfo: TaxiFareAndDistanceInfo? = null

        HttpEntity<String>(headers).let { entity ->
            RestTemplate().exchange(url, HttpMethod.GET, entity, String::class.java).also { response ->
                var node = ObjectMapper().readTree(response.body)

                if (node["routes"].isArray && node["routes"].size() > 0) {
                    routeInfo = TaxiFareAndDistanceInfo(
                        taxi = node["routes"][0]["summary"]["fare"]["taxi"].intValue(),
                        toll = node["routes"][0]["summary"]["fare"]["toll"].intValue(),
                        distance = node["routes"][0]["summary"]["distance"].intValue(),
                        duration = node["routes"][0]["summary"]["duration"].intValue()
                    )
                } else {
                    throw NullPointerException("[Exception] Kakao Api check")
                }
            }
        }

        return routeInfo
    }

}
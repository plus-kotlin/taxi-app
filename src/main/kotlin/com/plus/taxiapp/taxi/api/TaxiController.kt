package com.plus.taxiapp.taxi.api

import com.plus.taxiapp.taxi.api.request.TaxiInfoRegistRequest
import com.plus.taxiapp.taxi.api.response.TaxiInfoRegistResponse
import com.plus.taxiapp.taxi.domain.TaxiService
import com.plus.taxiapp.taxi.domain.command.TaxiCommand
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(value = ["/team7/api/v1/taxi"], produces = ["application/json"])
class TaxiController(
    private val taxiService: TaxiService,
) {
    @PostMapping("/register")
    fun driverRegister(
        @RequestBody taxiInfoRegistRequest: TaxiInfoRegistRequest,
    ): ResponseEntity<TaxiInfoRegistResponse>{
        val registedTaxiInfo = taxiService.driverRegister(
            TaxiCommand.Register.of(
                taxiNumber = taxiInfoRegistRequest.taxiNumber,
                driverId = taxiInfoRegistRequest.driverId,
                taxiType = taxiInfoRegistRequest.taxiType,
                taxiModel = taxiInfoRegistRequest.taxiModel,
            )
        )
        return ResponseEntity.ok().body(TaxiInfoRegistResponse.create(registedTaxiInfo));
    }
}
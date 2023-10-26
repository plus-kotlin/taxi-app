package com.plus.taxiapp.api.taxiDriver

import com.plus.taxiapp.api.taxiDriver.request.DriverGpsInfoRequest
import com.plus.taxiapp.api.taxiDriver.request.DriverTaxiRegistrationRequest
import com.plus.taxiapp.api.taxiDriver.response.DriverTaxiRegistrationResponse
import com.plus.taxiapp.domain.taxiDriver.command.TaxiDriverCommand
import com.plus.taxiapp.domain.taxiDriver.TaxiDriverService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/drivers/"], produces = ["application/json"])
class DriverApi(
    private val driverService: TaxiDriverService,
) {
    @PostMapping("/{driverId}/taxis")
    fun taxiRegister(
        @PathVariable driverId: String,
        @RequestBody driverTaxiRegistrationRequest: DriverTaxiRegistrationRequest,
    ): ResponseEntity<DriverTaxiRegistrationResponse> {
        driverTaxiRegistrationRequest.hasNoProblem()

        val registeredTaxiInfo = driverService.taxiInfoRegister(
            TaxiDriverCommand.Register(
                memberId = driverTaxiRegistrationRequest.memberId,
                driverId = driverId,
                taxiNumber = driverTaxiRegistrationRequest.taxiNumber,
                taxiType = driverTaxiRegistrationRequest.taxiType,
                taxiModel = driverTaxiRegistrationRequest.taxiModel,
            )
        )

        return ResponseEntity.ok().body(DriverTaxiRegistrationResponse.create(registeredTaxiInfo))
    }

    @PostMapping("/call/waiting")
    fun waitingCallDriver(
        @RequestBody driverGpsInfoRequest: DriverGpsInfoRequest
    ) {
        driverService.waitingCallDriver(driverGpsInfoRequest)
    }

    @PostMapping("/call/complete/{driverId}")
    fun callDrivingComplete(@PathVariable driverId: String) {
        driverService.callDrivingComplete(driverId)
    }
}
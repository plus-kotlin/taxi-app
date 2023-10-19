package com.plus.taxiapp.api.taxiDriver

import com.plus.taxiapp.api.taxiDriver.request.DriverTaxiRegistrationRequest
import com.plus.taxiapp.api.taxiDriver.response.DriverTaxiRegistrationResponse
import com.plus.taxiapp.domain.taxiDriver.command.DriverCommand
import com.plus.taxiapp.domain.taxiDriver.DriverService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/drivers/"], produces = ["application/json"])
class DriverApi(
    private val driverService: DriverService,
) {
    @PostMapping("/{driverId}/taxis")
    fun taxiRegister(
        @PathVariable driverId: String,
        @RequestBody driverTaxiRegistrationRequest: DriverTaxiRegistrationRequest,
    ): ResponseEntity<DriverTaxiRegistrationResponse> {
        driverTaxiRegistrationRequest.hasNoProblem()

        val registeredTaxiInfo = driverService.taxiInfoRegister(
            DriverCommand.Register.of(
                driverId = driverId,
                taxiNumber = driverTaxiRegistrationRequest.taxiNumber,
                taxiType = driverTaxiRegistrationRequest.taxiType,
                taxiModel = driverTaxiRegistrationRequest.taxiModel,
            )
        )

        return ResponseEntity.ok().body(DriverTaxiRegistrationResponse.create(registeredTaxiInfo))
    }
}
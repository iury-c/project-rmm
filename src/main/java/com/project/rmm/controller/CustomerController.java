package com.project.rmm.controller;

import com.project.rmm.dto.CalculateCostCustomerDeviceDto;
import com.project.rmm.dto.CustomerDeviceDto;
import com.project.rmm.model.Device;
import com.project.rmm.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
@Slf4j
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Calculate customer devices total cost")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer total cost",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Device.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)})
    private Double calculateCustomerCost(
            @Valid @RequestBody CalculateCostCustomerDeviceDto calculateCostCustomerDeviceDto) {

        List<Integer> deviceIds = calculateCostCustomerDeviceDto
                .getCustomerDeviceDtos()
                .stream().map(CustomerDeviceDto::getDeviceId)
                .collect(toList());

        log.info("Calculating costs for devices " + deviceIds);
        return customerService.calculateCost(calculateCostCustomerDeviceDto);
    }

}

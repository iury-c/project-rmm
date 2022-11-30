package com.project.rmm.controller;

import com.project.rmm.dto.ServiceDto;
import com.project.rmm.model.Service;
import com.project.rmm.service.ServiceService;
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

@RestController
@RequestMapping("/services")
@AllArgsConstructor
@Slf4j
public class ServicesController {
    private final ServiceService serviceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Service created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Service.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)})
    private Service createService(@Valid @RequestBody ServiceDto serviceDto) {
        log.info("Creating service - " + serviceDto);
        return serviceService.saveServiceEntity(serviceDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get device",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Service.class))}),
            @ApiResponse(responseCode = "404", description = "Service not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)})
    private Service getService(@PathVariable Integer id) {
        log.info("Getting service id - " + id);
        return serviceService.getServiceEntity(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Service.class))}),
            @ApiResponse(responseCode = "404", description = "Service not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteDevice(@PathVariable Integer id) {
        log.info("Deleting service id - " + id);
        serviceService.deleteServiceEntity(id);
    }
}

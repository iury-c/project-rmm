package com.project.rmm.controller;

import com.project.rmm.dto.DeviceDto;
import com.project.rmm.model.Device;
import com.project.rmm.service.DeviceService;
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
@RequestMapping("/devices")
@AllArgsConstructor
@Slf4j
public class DeviceController {
    private final DeviceService deviceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create device")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Device created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Device.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)})
    private Device createDevice(@Valid @RequestBody DeviceDto deviceDto) {
        log.info("Creating device - " + deviceDto);
        return deviceService.saveDeviceEntity(deviceDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve device")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get device",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Device.class))}),
            @ApiResponse(responseCode = "404", description = "Device not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)})
    private Device getDevice(@PathVariable Integer id) {
        log.info("Getting device id - " + id);
        return deviceService.getDeviceEntity(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete device")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Device deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Device.class))}),
            @ApiResponse(responseCode = "404", description = "Device not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteDevice(@PathVariable Integer id) {
        log.info("Deleting device id - " + id);
        deviceService.deleteDeviceEntity(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Create device")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Device updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Device.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)})
    private Device updateDevice(@Valid @RequestBody DeviceDto deviceDto, @PathVariable Integer id) {
        log.info("Updating device id - " + id);
        return deviceService.updateDeviceEntity(deviceDto, id);
    }
}

package com.project.rmm.dto;

import com.project.rmm.model.System;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DeviceDto {

    @NotNull(message = "System should not be null.")
    private System system;

    @NotBlank(message = "Type should not be blank.")
    private String type;
}

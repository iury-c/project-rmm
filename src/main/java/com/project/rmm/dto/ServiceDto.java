package com.project.rmm.dto;

import com.project.rmm.model.System;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class ServiceDto {

    @NotBlank(message = "Description should not be blank.")
    private String description;

    @PositiveOrZero(message = "Cost should not be less than zero.")
    private Double cost;

    @NotNull(message = "System should not be null.")
    private System system;
}

package com.project.rmm.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class CalculateCostCustomerDeviceDto {

    @NotEmpty
    private List<CustomerDeviceDto> customerDeviceDtos;

}

package com.project.rmm.service;

import com.project.rmm.dto.CalculateCostCustomerDeviceDto;
import com.project.rmm.model.System;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    public static final int DEVICE_UNIT_COST = 4;
    private final DeviceService deviceService;

    private final ServiceService serviceService;

    public Double calculateCost(CalculateCostCustomerDeviceDto calculateCostCustomerDeviceDto) {
        return calculateCostCustomerDeviceDto.getCustomerDeviceDtos().stream()
                .map(customerCostDto -> {
                    System deviceSystem = deviceService.getDeviceEntity(customerCostDto.getDeviceId()).getSystem();
                    List<com.project.rmm.model.Service> services = serviceService
                            .getServices(customerCostDto.getServiceDescriptions(), deviceSystem);
                    //TODO - Have device cost in database
                    return Double.sum(DEVICE_UNIT_COST, getServicesCostSum(services));
                })
                .reduce(0.0, Double::sum);
    }

    private Double getServicesCostSum(List<com.project.rmm.model.Service> services) {
        return services.stream()
                .map(service -> service.getCost())
                .reduce(0.0, Double::sum);
    }
}

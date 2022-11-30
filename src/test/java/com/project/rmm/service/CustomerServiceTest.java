package com.project.rmm.service;

import com.project.rmm.dto.CalculateCostCustomerDeviceDto;
import com.project.rmm.dto.CustomerDeviceDto;
import com.project.rmm.model.Device;
import com.project.rmm.model.Service;
import com.project.rmm.model.System;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    public System system;
    private int device1Id = 1;
    private int device2Id = 2;

    private Device device1;
    private Device device2;

    private Service service1;

    private Service service2;

    private Service service3;

    @Mock
    private DeviceService deviceService;

    @Mock
    private ServiceService serviceService;

    @InjectMocks
    private CustomerService customerService;

    private CalculateCostCustomerDeviceDto calculateCostCustomerDeviceDto;

    private List<String> device1ServiceDescriptions = Arrays.asList("Workstation");

    private List<String> device2ServiceDescriptions = Arrays.asList("Workstation", "Screen Sharing");

    @BeforeEach
    void setup() {
        calculateCostCustomerDeviceDto = new CalculateCostCustomerDeviceDto();

        CustomerDeviceDto customerDeviceDto1 = new CustomerDeviceDto();
        customerDeviceDto1.setDeviceId(device1Id);
        customerDeviceDto1.setServiceDescriptions(device1ServiceDescriptions);

        CustomerDeviceDto customerDeviceDto2 = new CustomerDeviceDto();
        customerDeviceDto2.setDeviceId(device2Id);
        customerDeviceDto2.setServiceDescriptions(device2ServiceDescriptions);

        system = new System("1", "Windows");

        device1 = new Device();
        device1.setSystem(system);

        device2 = new Device();
        device2.setSystem(system);

        service1 = new Service();
        service1.setCost(3.0);

        service2 = new Service();
        service2.setCost(5.0);

        service3 = new Service();
        service3.setCost(5.0);

        calculateCostCustomerDeviceDto.setCustomerDeviceDtos(Arrays.asList(customerDeviceDto1, customerDeviceDto2));
    }

    @Test
    void calculateCostTest() {
        Double expectedResult = 21d;

        when(deviceService.getDeviceEntity(device1Id)).thenReturn(device1);
        when(deviceService.getDeviceEntity(device2Id)).thenReturn(device2);

        when(serviceService.getServices(device1ServiceDescriptions, system)).thenReturn(Arrays.asList(service1));
        when(serviceService.getServices(device2ServiceDescriptions, system)).thenReturn(Arrays.asList(service2, service3));

        assertEquals(expectedResult, customerService.calculateCost(calculateCostCustomerDeviceDto));
    }
}

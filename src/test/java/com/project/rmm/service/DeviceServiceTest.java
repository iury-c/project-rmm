package com.project.rmm.service;

import com.project.rmm.database.DeviceRepository;
import com.project.rmm.dto.DeviceDto;
import com.project.rmm.model.Device;
import com.project.rmm.model.System;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeviceServiceTest {
    public Integer ID = 12345;
    public String deviceType = "Workstation";
    public System system = new System("1", "Windows");

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceService deviceService;

    private Device deviceEntity;

    @BeforeEach
    void setup(){
        deviceEntity = new Device(ID, system, deviceType);
    }

    @Test
    void getSampleData() {
        when(deviceRepository.findById(ID)).thenReturn(Optional.of(deviceEntity));

        Device actualEntity = deviceService.getDeviceEntity(ID);

        assertEquals(deviceEntity, actualEntity);
    }

    @Test
    void saveDeviceEntity() {
        Device saveDevice = new Device();
        saveDevice.setSystem(system);
        saveDevice.setType(deviceType);

        when(deviceRepository.save(saveDevice)).thenReturn(deviceEntity);

        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setSystem(system);
        deviceDto.setType(deviceType);

        assertEquals(deviceEntity, deviceService.saveDeviceEntity(deviceDto));
    }

    @Test
    void deleteSampleData(){
        doNothing().when(deviceRepository).deleteById(ID);
        deviceService.deleteDeviceEntity(ID);
        Mockito.verify(deviceRepository, times(1)).deleteById(ID);
    }

    //TODO - Add tests for the remaining methods in both controller package and service package
}

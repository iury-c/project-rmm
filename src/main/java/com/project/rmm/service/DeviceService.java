package com.project.rmm.service;

import com.project.rmm.database.DeviceRepository;
import com.project.rmm.dto.DeviceDto;
import com.project.rmm.exception.ResourceNotFoundException;
import com.project.rmm.model.Device;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DeviceService {
    private final DeviceRepository deviceRepository;

    public Device saveDeviceEntity(DeviceDto deviceDto) {
        //TODO - Add validation for verifying if the device has a valid system id
        //systemService.getSystemEntity(deviceDto.getSystem().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid system id")))

        Device device = new Device();
        device.setSystem(deviceDto.getSystem());
        device.setType(deviceDto.getType());

        log.info("Saving device - " + device);
        return deviceRepository.save(device);
    }

    public Device updateDeviceEntity(DeviceDto deviceDto, Integer id) {
        Device device = deviceRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        device.setSystem(deviceDto.getSystem());
        device.setType(deviceDto.getType());

        log.info("Updating device - " + device);
        return deviceRepository.save(device);
    }

    public Device getDeviceEntity(Integer id) {
        return deviceRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public void deleteDeviceEntity(Integer id) {
        deviceRepository.deleteById(id);
    }
}

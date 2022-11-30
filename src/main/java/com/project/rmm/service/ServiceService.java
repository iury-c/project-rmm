package com.project.rmm.service;

import com.project.rmm.database.ServiceRepository;
import com.project.rmm.dto.ServiceDto;
import com.project.rmm.exception.ResourceNotFoundException;
import com.project.rmm.model.System;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ServiceService {

    private final ServiceRepository serviceRepository;

    public com.project.rmm.model.Service saveServiceEntity(ServiceDto serviceDto) {
        //TODO - Add validation for verifying if the device has a valid system id
        //systemService.getSystemEntity(serviceDto.getSystem().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid system id")))

        com.project.rmm.model.Service service = new com.project.rmm.model.Service();
        service.setSystem(serviceDto.getSystem());
        service.setCost(service.getCost());
        service.setDescription(serviceDto.getDescription());

        log.info("Saving service - " + service);

        return serviceRepository.save(service);
    }

    public com.project.rmm.model.Service getServiceEntity(Integer id) {
        return serviceRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public List<com.project.rmm.model.Service> getServices(List<String> serviceDescriptions, System system) {
        return serviceRepository.findByDescriptionInAndSystem(serviceDescriptions, system);
    }

    public void deleteServiceEntity(Integer id) {
        serviceRepository.deleteById(id);
    }
}

package com.project.rmm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.rmm.ProjectRMMApplication;
import com.project.rmm.dto.DeviceDto;
import com.project.rmm.model.Device;
import com.project.rmm.model.System;
import com.project.rmm.service.DeviceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ProjectRMMApplication.class})
@WebMvcTest(DeviceController.class)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class DeviceControllerTest {
    public Integer ID = 12345;
    public String deviceType = "Workstation";
    public System system = new System("1", "Windows");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private DeviceService deviceService;

    private Device deviceEntity;

    @BeforeEach
    void setup() {
        deviceEntity = new Device(ID, system, deviceType);
    }

    @Test
    void getSampleData() throws Exception {
        when(deviceService.getDeviceEntity(ID)).thenReturn(deviceEntity);

        mockMvc.perform(get("/devices/" + ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(deviceEntity)));
    }

    @Test
    void postSampleData() throws Exception {
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setSystem(system);
        deviceDto.setType(deviceType);

        when(deviceService.saveDeviceEntity(deviceDto)).thenReturn(deviceEntity);

        String createDeviceDtoRequest = objectMapper.writeValueAsString(deviceDto);

        mockMvc.perform(post("/devices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createDeviceDtoRequest))
                .andExpect(status().isCreated())
                .andExpect(content().string(objectMapper.writeValueAsString(deviceEntity)));
    }

    //TODO - Add tests for the other methods in Controller Package
}

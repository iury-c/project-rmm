package com.project.rmm.database;

import com.project.rmm.model.Service;
import com.project.rmm.model.System;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Integer> {

    List<Service> findByDescriptionInAndSystem(List<String> descriptions, System system);
}

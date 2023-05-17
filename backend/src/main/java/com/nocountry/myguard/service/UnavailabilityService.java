package com.nocountry.myguard.service;

import com.nocountry.myguard.model.Unavailability;

import java.util.List;

public interface UnavailabilityService {
    Unavailability save(Unavailability unavailability);
    Unavailability update(Long id, Unavailability unavailability) throws Exception;

    Unavailability findById(Long id) throws Exception;

    List<Unavailability> findAll();

    void Delete(Long id) throws Exception;
}


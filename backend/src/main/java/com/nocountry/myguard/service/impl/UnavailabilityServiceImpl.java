package com.nocountry.myguard.service.impl;

import com.nocountry.myguard.model.Unavailability;
import com.nocountry.myguard.repository.UnavailabilityRepository;
import com.nocountry.myguard.service.UnavailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnavailabilityServiceImpl implements UnavailabilityService {

    @Autowired
    private UnavailabilityRepository unavailabilityRepository;

    @Override
    public Unavailability save(Unavailability unavailability) {



        return unavailabilityRepository.save(unavailability);
    }

    @Override
    public Unavailability update(Long id, Unavailability unavailabilityUpdated) throws Exception {


        Unavailability unavailability = findById(id);

        unavailability.setStartDate(unavailabilityUpdated.getStartDate());
        unavailability.setEndDate(unavailabilityUpdated.getEndDate());
        unavailability.setDuration(unavailabilityUpdated.getDuration());
        unavailability.setShift(unavailabilityUpdated.getShift());

        return unavailabilityRepository.save(unavailability);
    }

    @Override
    public Unavailability findById(Long id) throws Exception {

        if (!unavailabilityRepository.existsById(id)) {
            throw new Exception("No onCall with that id");
        }

        return unavailabilityRepository.findById(id).get();

    }

    @Override
    public List<Unavailability> findAll() {
        return unavailabilityRepository.findAll();
    }

    @Override
    public void Delete(Long id) throws Exception {

        unavailabilityRepository.delete(findById(id));

    }
}

package com.nocountry.myguard.service.impl;

import com.nocountry.myguard.model.Unavailability;
import com.nocountry.myguard.repository.UnavailabilityRepository;
import com.nocountry.myguard.service.UnavailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UnavailabilityServiceImpl implements UnavailabilityService {

    @Autowired
    private UnavailabilityRepository unavailabilityRepository;

    @Override
    public Unavailability save(Unavailability unavailability) throws Exception {

        if (unavailability.getStartDate() == null) throw new Exception("Start date can't be null");
        if (unavailability.getMonth() == null) throw new Exception("Month must be assigned to unavailability");
        if (unavailability.getEndDate() == null && unavailability.getDuration() == 0) throw new Exception("You must assign an end date or a duration to an unavailability");
        if (!unavailability.getMonth().isCorrectMonthByOnCallStartDate(unavailability.getStartDate())) throw new Exception("Incorrect month assigned by start date");
        if (unavailability.getUser() == null) throw new Exception("User must be assigned to unavailability");


        if (unavailability.getEndDate() == null && unavailability.getStartDate() != null && unavailability.getDuration() != 0) {
            unavailability.calculateEndDate(unavailability.getStartDate(),unavailability.getDuration());
        }

        if (unavailability.getStartDate() != null || unavailability.getEndDate() != null || unavailability.getDuration() == 0) {
            unavailability.calculateDuration(unavailability.getStartDate(), unavailability.getEndDate());
        }

        return unavailabilityRepository.save(unavailability);
    }

    @Override
    public Unavailability update(Long id, Unavailability unavailabilityUpdated) throws Exception {


        Unavailability unavailability = findById(id);

        unavailability.setStartDate(unavailabilityUpdated.getStartDate());
        unavailability.setEndDate(unavailabilityUpdated.getEndDate());
        unavailability.setDuration(unavailabilityUpdated.getDuration());

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

    @Override
    public List<Unavailability> findByDateTimeRange(LocalDateTime start, LocalDateTime end) throws Exception {

        if (start == null) throw new Exception("Start date can't be null");
        if (end == null) throw new Exception("End date can't be null");
        if (start.isAfter(end)) throw new Exception("Start date can't be after end date");

        return unavailabilityRepository.findByDateTimeRange(start, end);

    }
}

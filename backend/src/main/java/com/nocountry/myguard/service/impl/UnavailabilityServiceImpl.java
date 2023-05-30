package com.nocountry.myguard.service.impl;

import com.nocountry.myguard.model.Counter;
import com.nocountry.myguard.model.Month;
import com.nocountry.myguard.model.Unavailability;
import com.nocountry.myguard.model.User;
import com.nocountry.myguard.repository.CounterRepository;
import com.nocountry.myguard.repository.MonthRepository;
import com.nocountry.myguard.repository.UnavailabilityRepository;
import com.nocountry.myguard.repository.UserRepository;
import com.nocountry.myguard.service.OnCallService;
import com.nocountry.myguard.service.UnavailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UnavailabilityServiceImpl implements UnavailabilityService {


    private final UnavailabilityRepository unavailabilityRepository;

    private final OnCallServiceImpl onCallService;

    private final CounterRepository counterRepository;

    private final UserRepository userRepository;

    private final MonthRepository monthRepository;

    @Autowired
    public UnavailabilityServiceImpl(UnavailabilityRepository unavailabilityRepository, @Lazy OnCallServiceImpl onCallService, CounterRepository counterRepository, UserRepository userRepository, MonthRepository monthRepository) {
        this.unavailabilityRepository = unavailabilityRepository;
        this.onCallService = onCallService;
        this.counterRepository = counterRepository;
        this.userRepository = userRepository;
        this.monthRepository = monthRepository;
    }

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

        if (!findByDateTimeRange(unavailability.getStartDate(), unavailability.getEndDate()).isEmpty()) {
            throw new Exception("Can't create unavailability, there is already an unavailability created at the same time range");
        }

        if(!onCallService.findByDateTimeRange(unavailability.getStartDate(), unavailability.getEndDate()).isEmpty())
            throw new Exception("Can't create unavailability, there is already an on call created at the same time range");


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
        Unavailability unavailability = this.findById(id);
        /*if (unavailability.getStartDate().isBefore(LocalDateTime.now())){
            throw new Exception("The unavailability is in the past, you can't modify it."); //TODO Define this condition with business rules
        }*/

        User user = unavailability.getUser();
        Month month = unavailability.getMonth();

        //Remove unavailability from User and Month
        user.getUnavailabilities().remove(unavailability);
        month.getUnavailabilities().remove(unavailability);

        //Save changes
        userRepository.save(user);
        monthRepository.save(month);

        unavailabilityRepository.delete(unavailability);
    }

    @Override
    public List<Unavailability> findByDateTimeRange(LocalDateTime start, LocalDateTime end) throws Exception {

        if (start == null) throw new Exception("Start date can't be null");
        if (end == null) throw new Exception("End date can't be null");
        if (start.isAfter(end)) throw new Exception("Start date can't be after end date");

        return unavailabilityRepository.findByDateTimeRange(start, end);

    }
}

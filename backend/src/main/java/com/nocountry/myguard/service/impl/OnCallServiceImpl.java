package com.nocountry.myguard.service.impl;

import com.nocountry.myguard.model.Counter;
import com.nocountry.myguard.model.Month;
import com.nocountry.myguard.model.OnCall;
import com.nocountry.myguard.model.User;
import com.nocountry.myguard.repository.CounterRepository;
import com.nocountry.myguard.repository.MonthRepository;
import com.nocountry.myguard.repository.OnCallRepository;
import com.nocountry.myguard.repository.UserRepository;
import com.nocountry.myguard.service.OnCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OnCallServiceImpl implements OnCallService {


    private final OnCallRepository onCallRepository;
    private final CounterRepository counterRepository;
    private final MonthRepository monthRepository;
    private final UserRepository userRepository;
    private final UnavailabilityServiceImpl unavailabilityService;

    @Autowired
    public OnCallServiceImpl(OnCallRepository onCallRepository, CounterRepository counterRepository, MonthRepository monthRepository, UserRepository userRepository, UnavailabilityServiceImpl unavailabilityService) {
        this.onCallRepository = onCallRepository;
        this.counterRepository = counterRepository;
        this.monthRepository = monthRepository;
        this.userRepository = userRepository;
        this.unavailabilityService = unavailabilityService;
    }

    @Override
    public OnCall save(OnCall onCall) throws Exception {

        if (onCall.getStartDate() == null) throw new Exception("Start date can't be null");
        if (onCall.getMonth() == null) throw new Exception("Month must be assigned to on call");
        if (onCall.getEndDate() == null && onCall.getDuration() == 0)
            throw new Exception("You must assign an end date or a duration to an on call");
        if (!onCall.getMonth().isCorrectMonthByOnCallStartDate(onCall.getStartDate()))
            throw new Exception("Incorrect month assigned by start date");
        if (onCall.getUser() == null) throw new Exception("User must be assigned to on call");

        onCall.calculateShift(onCall.getStartDate());
        if (!onCall.getMonth().isCorrectOnCallShiftByMonthType(onCall.getShift(), onCall.getStartDate())) {
            throw new Exception("Incorrect shift by month type, select another date time (recommended) or change month type");
        }

        if (onCall.getEndDate() == null && onCall.getDuration() != 0) {
            onCall.calculateEndDate(onCall.getStartDate(), onCall.getDuration());
        }

        if (onCall.getEndDate() != null || onCall.getDuration() == 0) {
            onCall.calculateDuration(onCall.getStartDate(), onCall.getEndDate());
        }

        if (!unavailabilityService.findByDateTimeRange(onCall.getStartDate(), onCall.getEndDate()).isEmpty()) {
            throw new Exception("Can't create on call, there is an unavailability at the same time range");
        }

        Optional<Counter> existingCounter = counterRepository.findByUserAndMonth(onCall.getUser(), onCall.getMonth());
        Month month = onCall.getMonth();

        if (existingCounter.isEmpty()){
            Counter counter = new Counter(onCall.getUser(), onCall.getMonth());
            if(onCall.getMonth().isWeekend(onCall.getStartDate())){
                counter.addHsWeekend(onCall.getDuration());
            }else {
                counter.addHsWeek(onCall.getDuration());
            }
            counter.calculateOnCalls();
            counterRepository.save(counter);
            month.getCounters().add(counter);


        } else {
            Counter counter = existingCounter.get();
            if(onCall.getMonth().isWeekend(onCall.getStartDate())){
                counter.addHsWeekend(onCall.getDuration());
            }else {
                counter.addHsWeek(onCall.getDuration());
            }
            counter.calculateOnCalls();
            counterRepository.save(counter);
            month.getCounters().add(counter);

        }

        monthRepository.save(month);

        return onCallRepository.save(onCall);
    }

    @Override
    public OnCall update(Long id, OnCall onCallUpdated) throws Exception {

        OnCall onCall = findById(id);

        onCall.setStartDate(onCallUpdated.getStartDate());
        onCall.setEndDate(onCallUpdated.getEndDate());
        Duration duration = Duration.between(onCallUpdated.getStartDate(), onCallUpdated.getEndDate());
        long hours = duration.toHours();
        onCall.setDuration((int) hours);
        onCall.setShift(onCallUpdated.getShift());

        return onCallRepository.save(onCall);

    }

    @Override
    public OnCall findById(Long id) throws Exception {

        if (!onCallRepository.existsById(id)) {
            throw new Exception("No onCall with that id");
        }

        return onCallRepository.findById(id).get();

    }

    @Override
    public List<OnCall> findAll() {
        return onCallRepository.findAll();
    }

    @Override
    public void Delete(Long id) throws Exception {

        onCallRepository.delete(findById(id));

    }

    @Override
    public List<OnCall> findAllOnCallByDay(int day) {
        return onCallRepository.findAllByDay(day);
    }

    @Override
    public List<OnCall> findAllByMonth(int month) {
        return onCallRepository.findAllByMonth(month);
    }

    @Override
    public int getNumberOfOnCallByMonth(Long monthId, Long userId) throws Exception {

        // Bring user and month from database
        Month month = monthRepository.findById(monthId)
                .orElseThrow(() -> new Exception("No month with that id"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("No user with that id"));

        // Bring the counter of the user at that month
        Counter counter = counterRepository.findByUserAndMonth(user, month)
                .orElseThrow(() -> new Exception("No counter with that user and month"));

        // Calculate the number of on-calls
        //return counter.calculateOnCalls(counter.getCountHsWeek(), counter.getCountHsWeek());
        // TODO Change the name of the attribute to: return counter.getCountOnCall();

        return counter.calculateOnCalls();

    }

    @Override
    public List<OnCall> findAllByMonthAndUser(Long monthId, Long userId) throws Exception {

        // Bring user and month from database
        Month month = monthRepository.findById(monthId)
                .orElseThrow(() -> new Exception("No month with that id"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("No user with that id"));

        List<OnCall> onCalls = onCallRepository.findAllByMonthAndUser(month, user);

        if (onCalls.isEmpty()) {
           throw new Exception("No onCall with that user and month");
        }
        return onCalls;
    }

    @Override
    public List<OnCall> findByDateTimeRange(LocalDateTime start, LocalDateTime end) throws Exception {
        if (start == null || end == null) {
            throw new Exception("Start date and end date can't be null");
        }

        if (!unavailabilityService.findByDateTimeRange(start, end).isEmpty()) {
            throw new Exception("Doesn't exist an unavailability at the selected time range");
        }
        return onCallRepository.findByDateTimeRange(start, end);

    }


}

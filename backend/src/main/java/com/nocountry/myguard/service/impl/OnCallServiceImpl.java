package com.nocountry.myguard.service.impl;

import com.nocountry.myguard.model.Counter;
import com.nocountry.myguard.model.OnCall;
import com.nocountry.myguard.repository.CounterRepository;
import com.nocountry.myguard.repository.OnCallRepository;
import com.nocountry.myguard.service.OnCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class OnCallServiceImpl implements OnCallService {

    @Autowired
    private OnCallRepository onCallRepository;
    @Autowired
    private CounterRepository counterRepository;

    @Override
    public OnCall save(OnCall onCall) throws Exception {

        if (onCall.getStartDate() == null) throw new Exception("Start date can't be null");
        if (onCall.getMonth() == null) throw new Exception("Month must be assigned to on call");
        if (onCall.getEndDate() == null && onCall.getDuration() == 0) throw new Exception("You must assign an end date or a duration to an on call");
        if (!onCall.getMonth().isCorrectMonthByOnCallStartDate(onCall.getStartDate())) throw new Exception("Incorrect month assigned by start date");
        if (onCall.getUser() == null) throw new Exception("User must be assigned to on call");

        onCall.calculateShift(onCall.getStartDate());
        if (!onCall.getMonth().isCorrectOnCallShiftByMonthType(onCall.getShift(),onCall.getStartDate())) {
            throw new Exception("Incorrect shift by month type, select another date time (recommended) or change month type");
        }

        if (onCall.getEndDate() == null && onCall.getDuration() != 0) {
            onCall.calculateEndDate(onCall.getStartDate(),onCall.getDuration());
        }

        if (onCall.getEndDate() != null || onCall.getDuration() == 0) {
            onCall.calculateDuration(onCall.getStartDate(), onCall.getEndDate());
        }

        Optional<Counter> existingCounter = counterRepository.findByUserAndMonth(onCall.getUser(), onCall.getMonth());

        if (existingCounter.isEmpty()){
            Counter counter = counterRepository.save(new Counter(onCall.getUser(), onCall.getMonth()));
            //TODO Here put the Counter method to add countHs to the new created counter
        } else {
            //TODO Here put the Counter method to add countHs to existing counter
        }

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


}

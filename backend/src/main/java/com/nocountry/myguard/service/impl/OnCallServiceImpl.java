package com.nocountry.myguard.service.impl;

import com.nocountry.myguard.model.OnCall;
import com.nocountry.myguard.repository.OnCallRepository;
import com.nocountry.myguard.service.OnCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class OnCallServiceImpl implements OnCallService {

    @Autowired
    private OnCallRepository onCallRepository;

    @Override
    public OnCall save(OnCall onCall) {

        if (onCall.getEndDate() == null && onCall.getStartDate() != null && onCall.getDuration() != 0) {
            onCall.calculateEndDate(onCall.getStartDate(),onCall.getDuration());
        }


        if (onCall.getStartDate() != null || onCall.getEndDate() != null || onCall.getDuration() == 0) {

            onCall.calculateDuration(onCall.getStartDate(), onCall.getEndDate());

        }

        onCall.calculateShift(onCall.getStartDate());


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

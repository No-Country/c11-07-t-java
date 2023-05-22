package com.nocountry.myguard.service.impl;

import com.nocountry.myguard.model.OnCall;
import com.nocountry.myguard.repository.OnCallRepository;
import com.nocountry.myguard.service.OnCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class OnCallServiceImpl implements OnCallService {

    @Autowired
    private OnCallRepository onCallRepository;

    @Override
    public OnCall save(OnCall onCall) {

        if (onCall.getStartDate() != null || onCall.getEndDate() != null) {

            Duration duration = Duration.between(onCall.getStartDate(), onCall.getEndDate());
            long hours = duration.toHours();
            onCall.setDuration((int) hours);

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
}

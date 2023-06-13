package com.nocountry.myguard.service;

import com.nocountry.myguard.model.OnCall;
import org.springframework.data.repository.query.Param;


import java.time.LocalDateTime;
import java.util.List;

public interface OnCallService {

    OnCall save(OnCall onCall) throws Exception;
    OnCall update(Long id, OnCall onCall) throws Exception;

    OnCall findById(Long id) throws Exception;

    List<OnCall> findAll();

    void Delete(Long id) throws Exception;

    List<OnCall> findAllOnCallByDay(int day);


    List<OnCall> findAllByMonth(Long month) throws Exception;

    int getNumberOfOnCallByMonth(Long monthId, Long UserId) throws Exception;

    List<OnCall> findAllByMonthAndUser(Long monthId, Long userId) throws Exception;

    List<OnCall> findByDateTimeRange( LocalDateTime start, LocalDateTime end) throws Exception;

}

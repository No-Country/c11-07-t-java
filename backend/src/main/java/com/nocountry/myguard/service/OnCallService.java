package com.nocountry.myguard.service;

import com.nocountry.myguard.model.OnCall;

import java.util.List;

public interface OnCallService {

    OnCall save(OnCall onCall);
    OnCall update(Long id, OnCall onCall) throws Exception;

    OnCall findById(Long id) throws Exception;

    List<OnCall> findAll();

    void Delete(Long id) throws Exception;

    List<OnCall> findAllOnCallByDay(int day);
}

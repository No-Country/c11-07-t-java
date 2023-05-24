package com.nocountry.myguard.service;

import com.nocountry.myguard.enums.Specialization;
import com.nocountry.myguard.model.OnCall;
import com.nocountry.myguard.model.Professional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProfessionalService {
    Professional findById(Long id) throws Exception;
    List<Professional> getAll();
    Optional<Professional> findByName(String name);
    Optional<Professional> findByEnrolment(String enrolment);
    Optional<Professional> findByEmail(String email);
    Optional<Professional> findByDni(String dni);
    //Professional findByOnCalls(Long onCalls);
    Professional create(Professional professional) throws Exception;
    Professional update(Long id, Professional professional) throws Exception;
    void delete(Long id) throws Exception;
    public Professional addMonth2Professional(Long idProfessional, Long idMonth) throws Exception;

    public Professional removeMonth2Professional(Long idProfessional, Long idMonth) throws Exception;


    OnCall createOnCall(Long idProfessional, Long idMonth, LocalDateTime startDate, int duration, LocalDateTime endDate) throws Exception;
}

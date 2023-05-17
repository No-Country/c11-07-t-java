package com.nocountry.myguard.service;

import com.nocountry.myguard.model.Professional;

import java.util.List;

public interface ProfessionalService {
    Professional findById(Long id);
    List<Professional> getAll();
    Professional findByFullName(String name);
    Professional findByEnrolment(String enrolment);
    Professional findByEmail(String email);
    Professional findByOnCalls(Long onCalls);
    Professional create(Professional professional);
    Professional update(Long id, Professional professional);
}

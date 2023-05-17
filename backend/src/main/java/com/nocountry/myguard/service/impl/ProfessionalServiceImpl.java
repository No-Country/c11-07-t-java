package com.nocountry.myguard.service.impl;

import com.nocountry.myguard.model.Professional;
import com.nocountry.myguard.repository.ProfessionalRepository;
import com.nocountry.myguard.service.ProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionalServiceImpl implements ProfessionalService {
    @Autowired
    private ProfessionalRepository professionalRepository;

    @Override
    public Professional findById(Long id) {
        return null;
    }

    @Override
    public List<Professional> getAll() {
        return null;
    }

    @Override
    public Professional findByFullName(String name) {
        return null;
    }

    @Override
    public Professional findByEnrolment(String enrolment) {
        return null;
    }

    @Override
    public Professional findByEmail(String email) {
        return null;
    }

    @Override
    public Professional findByOnCalls(Long onCalls) {
        return null;
    }

    @Override
    public Professional create(Professional professional) {
        return null;
    }

    @Override
    public Professional update(Long id, Professional professional) {
        return null;
    }
}

package com.nocountry.myguard.service.impl;

import com.nocountry.myguard.enums.Role;
import com.nocountry.myguard.enums.Specialization;
import com.nocountry.myguard.model.Month;
import com.nocountry.myguard.model.OnCall;
import com.nocountry.myguard.model.Professional;
import com.nocountry.myguard.repository.MonthRepository;
import com.nocountry.myguard.repository.OnCallRepository;
import com.nocountry.myguard.repository.ProfessionalRepository;
import com.nocountry.myguard.service.ProfessionalService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessionalServiceImpl implements ProfessionalService {

    private final ProfessionalRepository professionalRepository;
    private final MonthServiceImpl monthService;
    private final OnCallServiceImpl onCallService;

    private final MonthRepository monthRepository;

    private final OnCallRepository onCallRepository;

    @Autowired
    public ProfessionalServiceImpl(ProfessionalRepository professionalRepository, MonthServiceImpl monthService, OnCallServiceImpl onCallService, MonthRepository monthRepository, OnCallRepository onCallRepository) {
        this.professionalRepository = professionalRepository;
        this.monthService = monthService;
        this.onCallService = onCallService;
        this.monthRepository = monthRepository;
        this.onCallRepository = onCallRepository;
    }

    @Override
    public Professional findById(Long id) throws Exception {
        if (!professionalRepository.existsById(id)) {
            throw new Exception("No professional with that id");
        }

        return professionalRepository.findById(id).get();
    }

    @Override
    public List<Professional> getAll() {
        return professionalRepository.findAll();
    }

    @Override
    public Optional<Professional> findByName(String name) {
        return professionalRepository.findByName(name);
    }

    @Override
    public Optional<Professional> findByEnrolment(String enrolment) {
        return professionalRepository.findByEnrolment(enrolment);
    }

    @Override
    public Optional<Professional> findByEmail(String email) {
        return professionalRepository.findByEmail(email);
    }

    @Override
    public Optional<Professional> findByDni(String dni) {
        return professionalRepository.findByPersonalID(dni);
    }
    /*@Override
    public Professional findByOnCalls(Long onCalls) {
        return null;
    }*/

    @Transactional
    @Override
    public Professional create(Professional professional) throws Exception {

        Optional<Professional> optionalUser = professionalRepository.findByPersonalID(professional.getPersonalID());

        if (optionalUser.isPresent())
            throw new RuntimeException("User already exists!");

        professional.setRole(Role.PROFESSIONAL);

        return professionalRepository.save(professional);
    }

    @Transactional
    @Override
    public Professional update(Long id, Professional professionalUpdate) throws Exception {

        Professional professional = findById(id);

        professional.setName(professionalUpdate.getName());
        professional.setLastName(professionalUpdate.getLastName());
        professional.setPersonalID(professionalUpdate.getPersonalID());
        professional.setEmail(professionalUpdate.getEmail());
        professional.setEnrolment(professionalUpdate.getEnrolment());
        professional.setPassword(professionalUpdate.getPassword());

        return professionalRepository.save(professional);
    }

    @Transactional
    @Override
    public void delete(Long id) throws Exception {

        professionalRepository.delete(findById(id));

    }

    @Override
    public Professional addMonth2Professional(Long idProfessional, Long idMonth) throws Exception {


        Professional professional = findById(idProfessional);

        professional.addMonth(monthService.findById(idMonth));

        return professionalRepository.save(professional);


    }

    @Override
    public Professional removeMonth2Professional(Long idProfessional, Long idMonth) throws Exception {


        Professional professional = findById(idProfessional);

        professional.removeMonth(monthService.findById(idMonth));

        return professionalRepository.save(professional);


    }


    public Professional addSpecialization2Professional(Long professionalId, Specialization specialization) {
        // Retrieve the Professional entity from the repository
        Optional<Professional> optionalProfessional = professionalRepository.findById(professionalId);
        if (optionalProfessional.isPresent()) {
            Professional professional = optionalProfessional.get();

            // Add the specialization to the professional
            professional.addSpecialization(specialization);

            // Save the updated Month entity back to the database
           return professionalRepository.save(professional);
        } else {
            // Handle the case where the Month entity with the given ID is not found
            throw new IllegalArgumentException("Professional not found with ID: " + professionalId);
        }

    }

    @Override
    public OnCall createOnCall(Long idProfessional, Long idMonth, LocalDateTime startDate, int duration, LocalDateTime endDate) throws Exception {



        Professional professional = findById(idProfessional);
        Month month = monthService.findById(idMonth);

        OnCall newOnCall = new OnCall();

        newOnCall.setStartDate(startDate);

        newOnCall.setDuration(duration);

        if (newOnCall.getEndDate() == null && newOnCall.getStartDate() != null && newOnCall.getDuration() != 0) {
            newOnCall.calculateEndDate(newOnCall.getStartDate(),newOnCall.getDuration());
        }


        if (newOnCall.getStartDate() != null || newOnCall.getEndDate() != null || newOnCall.getDuration() == 0) {

            newOnCall.calculateDuration(newOnCall.getStartDate(), newOnCall.getEndDate());

        }

        newOnCall.setEndDate(endDate);

        newOnCall.calculateShift(newOnCall.getStartDate());

        month.getProfessionals().add(professional);
        month.getOnCalls().add(newOnCall);
        professional.getMonths().add(month);


        monthRepository.save(month); // actualiza mes //TODO o crea uno nuevo
        return onCallRepository.save(newOnCall); // persiste nueva guardia


    }


}


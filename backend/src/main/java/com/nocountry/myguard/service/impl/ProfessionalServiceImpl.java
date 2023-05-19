package com.nocountry.myguard.service.impl;

import com.nocountry.myguard.model.Month;
import com.nocountry.myguard.model.Professional;
import com.nocountry.myguard.repository.ProfessionalRepository;
import com.nocountry.myguard.service.MonthService;
import com.nocountry.myguard.service.ProfessionalService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessionalServiceImpl implements ProfessionalService {

    @Autowired
    private ProfessionalRepository professionalRepository;
    private MonthServiceImpl monthService;

    @Override
    public Professional findById(Long id) throws Exception {
        if (!professionalRepository.existsById(id)){
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
    public Professional create(Professional professional) throws Exception{


        Optional<Professional> optLaptop = professionalRepository.findById(professional.getId());

        if (optLaptop.isPresent())
            throw new Exception("There's already an element with this id");

        return professionalRepository.save(professional);
    }

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
}

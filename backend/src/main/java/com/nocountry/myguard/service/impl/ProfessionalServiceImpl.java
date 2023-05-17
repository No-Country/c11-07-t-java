package com.nocountry.myguard.service.impl;

import com.nocountry.myguard.model.Professional;
import com.nocountry.myguard.repository.ProfessionalRepository;
import com.nocountry.myguard.service.ProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessionalServiceImpl implements ProfessionalService {
    @Autowired
    private ProfessionalRepository professionalRepository;

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
        return professionalRepository.findByDni(dni);
    }
    /*@Override
    public Professional findByOnCalls(Long onCalls) {
        return null;
    }*/

    @Override
    public Professional create(Professional professional) throws Exception{
        Optional<Professional> professionalOptional = professionalRepository.findById(professional.getId());

        if (professionalOptional.isPresent())
            throw new RuntimeException("There's already a professional with this id.");

        return professionalRepository.save(professional);
    }

    @Override
    public Professional update(Long id, Professional professionalUpdate) throws Exception {
        Professional professional = findById(id);

        professional.setName(professionalUpdate.getName());
        professional.setLastName(professionalUpdate.getLastName());
        professional.setDni(professionalUpdate.getDni());
        professional.setEmail(professionalUpdate.getEmail());
        professional.setEnrolment(professionalUpdate.getEnrolment());
        professional.setDni(professionalUpdate.getDni());
        professional.setPassword(professionalUpdate.getPassword());

        return professionalRepository.save(professional);
    }

    @Override
    public void Delete(Long id) throws Exception {

        professionalRepository.delete(findById(id));

    }
}

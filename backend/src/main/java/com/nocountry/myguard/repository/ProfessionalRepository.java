package com.nocountry.myguard.repository;

import com.nocountry.myguard.model.Month;
import com.nocountry.myguard.model.Professional;
import com.nocountry.myguard.service.ProfessionalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, Long> {
    Optional<Professional> findByName(String name);
    Optional<Professional> findByEnrolment(String enrolment);
    Optional<Professional> findByEmail(String email);
    Optional<Professional> findByPersonalID(String personalID);
    //Optional<Professional> findByOnCalls(String onCalls);

}

package com.nocountry.myguard.repository;

import com.nocountry.myguard.model.Unavailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnavailabilityRepository extends JpaRepository<Unavailability,Long> {

}





package com.nocountry.myguard.repository;

import com.nocountry.myguard.model.Unavailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UnavailabilityRepository extends JpaRepository<Unavailability,Long> {


    @Query("SELECT u FROM Unavailability u WHERE u.startDate <= :start AND u.endDate >= :end")
    List<Unavailability> findByDateTimeRange(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

}





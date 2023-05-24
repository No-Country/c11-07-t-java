package com.nocountry.myguard.repository;

import com.nocountry.myguard.model.OnCall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OnCallRepository extends JpaRepository<OnCall,Long> {

    @Query("SELECT o FROM OnCall o WHERE DAY(o.startDate) = :day")
    List<OnCall> findAllByDay(@Param("day") int day);

}

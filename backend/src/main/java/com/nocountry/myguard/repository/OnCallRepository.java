package com.nocountry.myguard.repository;

import com.nocountry.myguard.model.Month;
import com.nocountry.myguard.model.OnCall;
import com.nocountry.myguard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OnCallRepository extends JpaRepository<OnCall,Long> {

    @Query("SELECT o FROM OnCall o WHERE DAY(o.startDate) = :day")
    List<OnCall> findAllByDay(@Param("day") int day);

    @Query("SELECT o FROM OnCall o WHERE MONTH(o.startDate) = :month")
    List<OnCall> findAllByMonth(@Param("month") Long month);

    List<OnCall> findAllByMonthAndUser(Month month, User user);

    @Query("SELECT u FROM OnCall u " +
            "WHERE (u.startDate >= :start AND u.endDate <= :end) " +
            "OR (u.startDate < :start AND u.endDate > :start) " +
            "OR (u.startDate < :end AND u.endDate > :end)")
    List<OnCall> findByDateTimeRange(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);


}

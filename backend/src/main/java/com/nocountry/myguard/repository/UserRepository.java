package com.nocountry.myguard.repository;

import com.nocountry.myguard.enums.Specialization;
import com.nocountry.myguard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    List<User> findAllBySpecialization(Specialization specialization);
    Optional <User> findByUsername(String username);
    Optional<User> findByName(String name);
    Optional<User> findByEnrolment(String enrolment);
    Optional<User> findByEmail(String email);
    Optional<User> findByPersonalID(String personalID);

    boolean existsByEmail(String email);

    @Query("SELECT DISTINCT u FROM User u " +
            "JOIN u.onCalls oc " +
            "WHERE u.specialization = :specialization " +
            "AND ((oc.startDate >= :start AND oc.endDate <= :end) " +
            "OR (oc.startDate < :start AND oc.endDate > :start) " +
            "OR (oc.startDate < :end AND oc.endDate > :end))")
    List<User> findAllBySpecializationAndOnCallsRangeTime(
            @Param("specialization") Specialization specialization,
            @Param("start") LocalDateTime startDate,
            @Param("end") LocalDateTime endDate);


    @Query("SELECT DISTINCT u FROM User u " +
            "JOIN u.unavailabilities un " +
            "WHERE u.specialization = :specialization " +
            "AND ((un.startDate >= :start AND un.endDate <= :end) " +
            "OR (un.startDate < :start AND un.endDate > :start) " +
            "OR (un.startDate < :end AND un.endDate > :end))")
    List<User> findAllBySpecializationAndUnavailabilityRangeTime(
            @Param("specialization") Specialization specialization,
            @Param("start") LocalDateTime startDate,
            @Param("end") LocalDateTime endDate);

    @Query("SELECT DISTINCT u FROM User u " +
            "JOIN u.unavailabilities un " +
            "WHERE u.id = :id " +
            "AND ((un.startDate >= :start AND un.endDate <= :end) " +
            "OR (un.startDate < :start AND un.endDate > :start) " +
            "OR (un.startDate < :end AND un.endDate > :end))")
    List<User> getUserByIdAndUnavailabilityRangeTime(
            @Param("id") Long userId,
            @Param("start") LocalDateTime startDate,
            @Param("end") LocalDateTime endDate);

    @Query("SELECT DISTINCT u FROM User u " +
            "JOIN u.onCalls oc " +
            "WHERE u.id = :id " +
            "AND ((oc.startDate >= :start AND oc.endDate <= :end) " +
            "OR (oc.startDate < :start AND oc.endDate > :start) " +
            "OR (oc.startDate < :end AND oc.endDate > :end))")
    List<User> getUserByIdAndOnCallRangeTime(
            @Param("id") Long idUser,
            @Param("start") LocalDateTime startDate,
            @Param("end") LocalDateTime endDate);


    boolean existsByUsername (String username);

}

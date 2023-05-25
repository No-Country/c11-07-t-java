package com.nocountry.myguard.repository;

import com.nocountry.myguard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional <User> findByUsername(String username);
    Optional<User> findByName(String name);
    Optional<User> findByEnrolment(String enrolment);
    Optional<User> findByEmail(String email);
    Optional<User> findByPersonalID(String personalID);

    boolean existsByEmail(String email);

    boolean existsByUsername (String username);

    //Optional<Professional> findByOnCalls(String onCalls);

}

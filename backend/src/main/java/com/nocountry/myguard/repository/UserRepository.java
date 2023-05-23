package com.nocountry.myguard.repository;

import com.nocountry.myguard.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional <User> findByUsername(String username);


}

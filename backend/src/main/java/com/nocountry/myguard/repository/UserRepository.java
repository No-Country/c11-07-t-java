package com.nocountry.myguard.repository;

import com.nocountry.myguard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

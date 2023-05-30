package com.nocountry.myguard.repository;

import com.nocountry.myguard.model.Counter;
import com.nocountry.myguard.model.Month;
import com.nocountry.myguard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CounterRepository extends JpaRepository<Counter, Long> {
    Optional<Counter> findByUserAndMonth(User user, Month month);
}

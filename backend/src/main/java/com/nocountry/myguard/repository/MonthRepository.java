package com.nocountry.myguard.repository;

import com.nocountry.myguard.enums.Type;
import com.nocountry.myguard.model.Month;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MonthRepository extends JpaRepository<Month, Long> {

    Optional<Month> findById(Long idMonth);

    Optional<Month> findByNameAndYear(String name, int year);

    List<Month> findAllByYear(int year);

    Optional<Month> findByType(Type type);


}

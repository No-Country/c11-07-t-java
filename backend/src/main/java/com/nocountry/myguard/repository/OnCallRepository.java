package com.nocountry.myguard.repository;

import com.nocountry.myguard.model.OnCall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnCallRepository extends JpaRepository<OnCall,Long> {

}

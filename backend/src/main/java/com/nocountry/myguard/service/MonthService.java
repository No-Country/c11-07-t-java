package com.nocountry.myguard.service;

import com.nocountry.myguard.model.Month;
import javax.management.ServiceNotFoundException;
import java.util.List;
import java.util.Optional;

public interface MonthService {

    List<Month> getAll();
    Month findById(Long id) throws ServiceNotFoundException;
    List<Month> findAllByYear(int year) throws ServiceNotFoundException;
    Month findByNameAndYear(String name, int year) throws ServiceNotFoundException;
    Month create(Month month) throws Exception;
    Month update(Long id, Month month) throws Exception;
    void delete(Long id) throws Exception;
}

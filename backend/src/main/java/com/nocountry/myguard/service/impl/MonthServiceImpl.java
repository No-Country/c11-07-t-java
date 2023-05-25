package com.nocountry.myguard.service.impl;

import com.nocountry.myguard.model.Month;
import com.nocountry.myguard.repository.MonthRepository;
import com.nocountry.myguard.service.MonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.ServiceNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class MonthServiceImpl implements MonthService {


    @Autowired
    MonthRepository monthRepository;

    @Override
    public List<Month> getAll() {
        return null;
    }

    @Override
    public Month findById(Long id) throws ServiceNotFoundException {

        Optional<Month> optMonth = monthRepository.findById(id);

        if (optMonth.isEmpty())
            throw new ServiceNotFoundException("Not found month with that id");

        return optMonth.get();

    }

    @Override
    public List<Month> findAllByYear(int year) throws ServiceNotFoundException {

        List<Month> optMonths = monthRepository.findAllByYear(year);

        if (optMonths.isEmpty())
            throw new ServiceNotFoundException("No months with that year");
        return optMonths;
    }


    @Override
    public Month findByNameAndYear(String name, int year) throws ServiceNotFoundException {

        Optional<Month> optMonth = monthRepository.findByNameAndYear(name, year);

        if (optMonth.isEmpty())
            throw new ServiceNotFoundException("Not found month with that name and year");

        return optMonth.get();
    }

    @Override
    public Month create(Month month) throws Exception {

        return monthRepository.save(month);

    }

    @Override
    public Month update(Long id, Month monthUpdated) throws Exception {

        Month month = findById(id);

        // You can just modify type and year in the update method

        month.setType(monthUpdated.getType());
        month.setName(monthUpdated.getName());

        return monthRepository.save(month);

    }

    @Override
    public void delete(Long id) throws Exception {

        monthRepository.delete(findById(id));

    }


}

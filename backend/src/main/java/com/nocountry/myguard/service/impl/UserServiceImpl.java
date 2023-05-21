package com.nocountry.myguard.service.impl;

import com.nocountry.myguard.enums.Role;
import com.nocountry.myguard.model.User;
import com.nocountry.myguard.repository.UserRepository;
import com.nocountry.myguard.service.UserService;
import jakarta.transaction.Transactional;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class  UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Transactional
    @Override
    public User save(User user) throws Exception {

        Optional<User> optUser = userRepository.findByUsername(user.getUsername());

        if (optUser.isPresent())
            throw new ServiceException("There's already a user with that username");


        return userRepository.save(user);
    }

    @Override
    public User update(Long id, User userUpdate) throws Exception {

        User user = findById(id);

        user.setUsername(userUpdate.getUsername());
        user.setEmail(userUpdate.getEmail());
        user.setPassword(userUpdate.getPassword());
        user.setRole(Role.PROFESSIONAL);

        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) throws Exception {

        if (!userRepository.existsById(id))
            throw new RuntimeException("No user exists with that id");

        return userRepository.findById(id).get();



    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void Delete(Long id) throws Exception {

    }

    @Override
    public Optional<User> findByUsername(String username){

        return userRepository.findByUsername(username);

    }
}
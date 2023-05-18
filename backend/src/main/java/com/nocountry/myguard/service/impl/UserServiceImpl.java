package com.nocountry.myguard.service.impl;

import com.nocountry.myguard.enums.Role;
import com.nocountry.myguard.model.User;
import com.nocountry.myguard.repository.UserRepository;
import com.nocountry.myguard.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public User save(User user) throws RuntimeException {

    /*    Optional<User> optionalUser = userRepository.findById(user.getId());

        if (optionalUser.isPresent())
            throw new RuntimeException("User already exists!"); */

        user.setPassword(passwordEncoder.encode(user.getPassword()));

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
        return null;
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
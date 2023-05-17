package com.nocountry.myguard.service.impl;

import com.nocountry.myguard.enums.Role;
import com.nocountry.myguard.model.User;
import com.nocountry.myguard.repository.UserRepository;
import com.nocountry.myguard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

@Autowired
private UserRepository userRepository;
    @Override
    public User save(User user) {

        Optional<User> optionalUser = userRepository.findById(user.getId());

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
}
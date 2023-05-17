package com.nocountry.myguard.service;

import com.nocountry.myguard.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
@GetMapping
@RequestMapping

public class UserService{
    @Autowired
    UserRepository userRepository;

    public List<User> list() {
        return userRepository.findAll();
    }
    public Optional<User> getOne(int id) {
        return userRepository.findById(id);
    }
    public void save(User usuario) {
        userRepository.save(usuario);
    }
    public void delete(int id) {
        userRepository.deleteById(id);
    }
    public boolean existById(int id) {
        return userRepository.existsById(id);
    }
}
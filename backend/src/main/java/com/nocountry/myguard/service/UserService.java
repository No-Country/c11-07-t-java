package com.nocountry.myguard.service;


import com.nocountry.myguard.auth.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user) throws Exception;
    User update(Long id, User user) throws Exception;

    User findById(Long id) throws Exception;

    List<User> findAll();

    void Delete(Long id) throws Exception;

    Optional <User> findByUsername(String username);
}

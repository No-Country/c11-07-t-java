package com.nocountry.myguard.service;


import com.nocountry.myguard.model.User;

import java.util.List;

public interface UserService {

    User save(User user);
    User update(Long id, User user) throws Exception;

    User findById(Long id) throws Exception;

    List<User> findAll();

    void Delete(Long id) throws Exception;
}

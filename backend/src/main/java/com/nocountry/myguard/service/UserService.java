package com.nocountry.myguard.service;


import com.nocountry.myguard.auth.model.authentication.AuthenticationRequest;
import com.nocountry.myguard.auth.model.authentication.AuthenticationResponse;
import com.nocountry.myguard.auth.model.authentication.RegisterRequest;
import com.nocountry.myguard.model.OnCall;
import com.nocountry.myguard.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserService {


    User update(Long id, User user) throws Exception;

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    User findById(Long id) throws Exception;

    Optional<User> findByUsername(String username);

    List<User> getAll();

    Optional<User> findByName(String name);

    Optional<User> findByEnrolment(String enrolment);

    Optional<User> findByEmail(String email);

    Optional<User> findByDni(String dni);

      void delete(Long id) throws Exception;


}

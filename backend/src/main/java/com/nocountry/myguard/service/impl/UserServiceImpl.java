package com.nocountry.myguard.service.impl;

import com.nocountry.myguard.auth.model.authentication.AuthenticationRequest;
import com.nocountry.myguard.auth.model.authentication.AuthenticationResponse;
import com.nocountry.myguard.auth.model.authentication.RegisterRequest;
import com.nocountry.myguard.auth.service.AuthenticationService;
import com.nocountry.myguard.enums.Specialization;
import com.nocountry.myguard.model.User;
import com.nocountry.myguard.repository.MonthRepository;
import com.nocountry.myguard.repository.OnCallRepository;
import com.nocountry.myguard.repository.UserRepository;
import com.nocountry.myguard.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MonthServiceImpl monthService;
    private final OnCallServiceImpl onCallService;

    private final MonthRepository monthRepository;

    private final OnCallRepository onCallRepository;

    private final AuthenticationService authenticationService;

    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, MonthServiceImpl monthService, OnCallServiceImpl onCallService, MonthRepository monthRepository, OnCallRepository onCallRepository, AuthenticationService authenticationService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.monthService = monthService;
        this.onCallService = onCallService;
        this.monthRepository = monthRepository;
        this.onCallRepository = onCallRepository;
        this.authenticationService = authenticationService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        // Delegate the registration to the AuthenticationService
        return authenticationService.register(request);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // Delegate the authentication to the AuthenticationService
        return authenticationService.authenticate(request);
    }


    @Override
    public User findById(Long id) throws Exception {
        if (!userRepository.existsById(id)) {
            throw new Exception("No professional with that id");
        }

        return userRepository.findById(id).get();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public Optional<User> findByEnrolment(String enrolment) {
        return userRepository.findByEnrolment(enrolment);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByDni(String dni) {
        return userRepository.findByPersonalID(dni);
    }
    /*@Override
    public Professional findByOnCalls(Long onCalls) {
        return null;
    }*/


    @Transactional
    @Override
    public User update(Long id, User professionalUpdate) throws Exception {

        User professional = findById(id);

        if (professionalUpdate.getName() != null) {
            professional.setName(professionalUpdate.getName());
        }
        if (professionalUpdate.getLastName() != null) {
            professional.setLastName(professionalUpdate.getLastName());
        }
        if (professionalUpdate.getPersonalID() != null) {
            professional.setPersonalID(professionalUpdate.getPersonalID());
        }
        if (professionalUpdate.getEmail() != null) {
            professional.setEmail(professionalUpdate.getEmail());
        }
        if (professionalUpdate.getEnrolment() != null) {
            professional.setEnrolment(professionalUpdate.getEnrolment());
        }
        if (professionalUpdate.getPassword() != null) {
            professional.setPassword(passwordEncoder.encode(professionalUpdate.getPassword()));
        }

        if (professionalUpdate.getSpecialization() != null) {
            professional.setSpecialization(professionalUpdate.getSpecialization());
        }


        return userRepository.save(professional);
    }

    @Transactional
    @Override
    public void delete(Long id) throws Exception {

        userRepository.delete(findById(id));

    }


    public User addSpecialization2Professional(Long professionalId, Specialization specialization) {
        // Retrieve the Professional entity from the repository
        Optional<User> optionalProfessional = userRepository.findById(professionalId);
        if (optionalProfessional.isPresent()) {
            User professional = optionalProfessional.get();

            // Add the specialization to the professional
            professional.addSpecialization(specialization);

            // Save the updated Month entity back to the database
            return userRepository.save(professional);
        } else {
            // Handle the case where the Month entity with the given ID is not found
            throw new IllegalArgumentException("Professional not found with ID: " + professionalId);
        }

    }


    //This method is used to validate the quantity of calls by specialization: just only one on call per specialization
    public Boolean validateQuantityOnCallsBySpecialization(Specialization specialization, LocalDateTime startDate, LocalDateTime endDate) {

        return !userRepository.findAllBySpecializationAndOnCallsRangeTime(specialization, startDate, endDate).isEmpty();
    }


    //This method is used to validate the quantity of unavailabilities by specialization: if all the users are unavailable, then the last one cant be unavailable
    public Boolean validateQuantityUnavailabilityBySpecialization(Specialization specialization, LocalDateTime startDate, LocalDateTime endDate) {

        List<User> usersWithSpecialization = userRepository.findAllBySpecialization(specialization);
        System.out.println(userRepository.findAllBySpecialization(specialization).size());
        List<User> usersWithUnavailability = userRepository.findAllBySpecializationAndUnavailabilityRangeTime(specialization, startDate, endDate);
        System.out.println(userRepository.findAllBySpecializationAndUnavailabilityRangeTime(specialization, startDate, endDate).size());

        List<User> availableUsers = new ArrayList<>();

        for (User user : usersWithSpecialization) {
            if (!usersWithUnavailability.contains(user)) {
                availableUsers.add(user);
            }
        }

        System.out.println(availableUsers.size());
        return availableUsers.size() == 1;
    }

    public Boolean isEmptyUnavailabilitiesByUserIdAndRangeTime(Long idUser, LocalDateTime startDate, LocalDateTime endDate) {

        return !userRepository.getUserByIdAndUnavailabilityRangeTime(idUser, startDate, endDate).isEmpty();


    }

    public Boolean isEmptyOnCallsByUserIdAndRangeTime(Long idUser, LocalDateTime startDate, LocalDateTime endDate) {

        return !userRepository.getUserByIdAndOnCallRangeTime(idUser, startDate, endDate).isEmpty();

    }


}


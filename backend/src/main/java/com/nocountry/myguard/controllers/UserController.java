package com.nocountry.myguard.controllers;

import com.nocountry.myguard.enums.Specialization;
import com.nocountry.myguard.model.OnCall;
import com.nocountry.myguard.model.User;
import com.nocountry.myguard.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("")
    public ResponseEntity<List<User>> getProfessionals() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        if (id < 1) return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok(userService.findById(id));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("getByParam")
    public ResponseEntity<User> findByParam(@RequestParam(required = false) String name,
                                                    @RequestParam(required = false) String enrolment,
                                                    @RequestParam(required = false) String email,
                                                    @RequestParam(required = false) String dni
                                                    //,@RequestParam(required = false) Long onCalls
    ) {

        if (name != null) {
            return this.releaseProfessionalInResponseEntity(userService.findByName(name));
        } else if (enrolment != null) {
            return this.releaseProfessionalInResponseEntity(userService.findByEnrolment(enrolment));
        } else if (email != null) {
            return this.releaseProfessionalInResponseEntity(userService.findByEmail(email));
        } else if (dni != null) {
            return this.releaseProfessionalInResponseEntity(userService.findByDni(dni));
        }//else if (onCalls != null) {return this.releaseProfessionalInResponseEntity(professionalService.findByOnCalls(onCalls));}

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestParam Long id, @RequestBody User professional) throws Exception {


        if (id < 1 || professional == null ) return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok(userService.update(id, professional));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> Delete(Long id) {

        if (id < 1) return ResponseEntity.badRequest().build();

        try {
            userService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }

    private ResponseEntity<User> releaseProfessionalInResponseEntity(Optional<User> professionalOptional) {
        if (professionalOptional.isPresent()) {
            return ResponseEntity.ok(professionalOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{professionalId}/specializations")
    public ResponseEntity<User> addSpecialization2Professional(@PathVariable Long professionalId,
                                                               @RequestBody Specialization specialization) {


        if (professionalId == null || specialization == null) return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok(userService.addSpecialization2Professional(professionalId, specialization));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }




}
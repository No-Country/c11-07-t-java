package com.nocountry.myguard.controllers;

import com.nocountry.myguard.enums.Specialization;
import com.nocountry.myguard.model.OnCall;
import com.nocountry.myguard.model.User;
import com.nocountry.myguard.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
@Tag(name = "User" , description = "User Controller")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("")
    public ResponseEntity<List<User>> getProfessionals() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    public ResponseEntity<User> findById(
            @Parameter(name = "id", description = "User id to get", required = true)
            @PathVariable Long id) {
        if (id < 1) return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok(userService.findById(id));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("findbyusername")
    public ResponseEntity<User> findByUsername(@RequestParam String username){
        User user = null;
        if (userService.findByUsername(username).isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            user = userService.findByUsername(username).get();
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("getByParam")
    @Operation(summary = "Get user by param")
    public ResponseEntity<User> findByParam(
            @Parameter(name = "name", description = "User name to get", required = true)
            @RequestParam(required = false) String name,
            @Parameter(name = "enrolment", description = "User enrolment to get", required = true)
            @RequestParam(required = false) String enrolment,
            @Parameter(name = "email", description = "User email to get", required = true)
            @RequestParam(required = false) String email,
            @Parameter(name = "dni", description = "User dni to get", required = true)
            @RequestParam(required = false) String dni

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
    @Operation(summary = "Update user by id")
    public ResponseEntity<User> update(
            @Parameter(name = "id", description = "User id to update", required = true)
            @PathVariable Long id,
            @Parameter(name = "professional", description = "User professional to update", required = true)
            @RequestBody User professional) throws Exception {


        if (id < 1 || professional == null ) return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok(userService.update(id, professional));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by id")
    public ResponseEntity<User> Delete(
            @Parameter(name = "id", description = "User id to delete", required = true)
            @RequestParam Long id) {

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
    @Operation(summary = "Add specialization to professional")
    public ResponseEntity<User> addSpecialization2Professional(
            @Parameter(name = "professionalId", description = "Professional id to add specialization to", required = true)
            @PathVariable Long professionalId,
            @Parameter(name = "specialization", description = "Specialization to add", required = true)
            @RequestBody Specialization specialization) {


        if (professionalId == null || specialization == null) return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok(userService.addSpecialization2Professional(professionalId, specialization));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }

}
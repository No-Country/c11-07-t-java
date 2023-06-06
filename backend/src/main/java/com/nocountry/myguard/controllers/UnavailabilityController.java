package com.nocountry.myguard.controllers;

import java.util.List;

import com.nocountry.myguard.model.Unavailability;
import com.nocountry.myguard.service.impl.UnavailabilityServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/unavailability/")
@Tag(name = "Unavailability" , description = "Unavailability Controller")
public class UnavailabilityController {
    @Autowired
    private  UnavailabilityServiceImpl unavailabilityService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "create a new Unavailability")
    public ResponseEntity<Unavailability> create(
            @Parameter(name = "unavailability", description = "new Unavailability Body to create", required = true)
            @RequestBody  Unavailability unavailability){

        if (unavailability == null)
            return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok(unavailabilityService.save(unavailability));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "update an Unavailability")
    public ResponseEntity<Unavailability> update(
            @Parameter(name = "unavailability id", description = "Unavailability id to update", required = true)
            @RequestParam Long id ,
            @Parameter(name = "unavailability", description = "Unavailability Body with new values to update", required = true)
            @RequestBody  Unavailability unavailabilityUpdate) throws Exception{

        if (id == 0 || unavailabilityUpdate == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(unavailabilityService.update(id, unavailabilityUpdate));

    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "get an Unavailability by id")
    public ResponseEntity<Unavailability> findById(
            @Parameter(name = "id Unavailability", description = "get an Unavailability by id", required = true)
            @PathVariable Long id) throws Exception {

        if (id == 0)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(unavailabilityService.findById(id));

    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "get all Unavailability")
    public ResponseEntity<List<Unavailability>> findAll(){

        return ResponseEntity.ok(unavailabilityService.findAll());

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "delete an Unavailability")
    ResponseEntity<Unavailability> Delete(
            @Parameter(name = "id Unavailability", description = "Unavailability id to delete", required = true)
            @RequestParam Long id){

        if (id == 0)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.noContent().build();

    }
}

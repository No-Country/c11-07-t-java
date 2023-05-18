package com.nocountry.myguard.controllers;

import java.util.List;

import com.nocountry.myguard.model.Unavailability;
import com.nocountry.myguard.service.impl.UnavailabilityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/unavailability/")
public class UnavailabilityController {
    @Autowired
    private  UnavailabilityServiceImpl unavailabilityService;

    @PostMapping
    public ResponseEntity<Unavailability> create(@RequestBody  Unavailability unavailability){

        if (unavailability == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(unavailabilityService.save(unavailability));

    }

    @PutMapping
    public ResponseEntity<Unavailability> update(@RequestParam Long id , @RequestBody  Unavailability unavailabilityUpdate) throws Exception{

        if (id == 0 || unavailabilityUpdate == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(unavailabilityService.update(id, unavailabilityUpdate));

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Unavailability> findById(@PathVariable Long id) throws Exception {

        if (id == 0)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(unavailabilityService.findById(id));

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Unavailability>> findAll(){

        return ResponseEntity.ok(unavailabilityService.findAll());

    }

    @DeleteMapping("/{id}")
    ResponseEntity<Unavailability> Delete(Long id){

        if (id == 0)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.noContent().build();

    }
}

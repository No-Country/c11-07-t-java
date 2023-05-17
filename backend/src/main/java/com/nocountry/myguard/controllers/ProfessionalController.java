package com.nocountry.myguard.controllers;

import com.nocountry.myguard.model.OnCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/professionals")
public class ProfessionalController {
    @Autowired
    private ProfessionalService professionalService;

    @GetMapping("")
    public ResponseEntity<List<Professional>> getProfessionals(){
        return ResponseEntity.ok(professionalService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professional> findById(@PathVariable Long id){
        if (id < 1) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(professionalService.findById(id));

    }

    @GetMapping
    public ResponseEntity<Professional> findByParam(@RequestParam(required = false) String name,
                                                 @RequestParam(required = false) String enrolment,
                                                 @RequestParam(required = false) String email,
                                                 @RequestParam(required = false) Long onCalls) {

        Professional professional;

        if (name != null) {
            professional = professionalService.findByFullName(name);
        } else if (enrolment != null) {
            professional = professionalService.findByEnrolment(enrolment);
        } else if (email != null) {
            professional = professionalService.findByEmail(email);
        } else if (onCalls != null) {
            professional = professionalService.findByOnCalls(onCalls);
        } else {
            return ResponseEntity.badRequest().build();
        }

        if (professional != null) {
            return ResponseEntity.ok(professional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Professional> create(@RequestBody Professional professional){
        if (professional == null) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(professionalService.create(professional));
    }

    @PutMapping
    public ResponseEntity<Professional> update(@RequestParam Long id , @RequestBody Professional professional) throws Exception{

        if (id < 1 || professional == null) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(professionalService.update(id, professional));
    }

    @DeleteMapping("/{id]")
    public ResponseEntity<Professional> Delete(Long id){

        if (id == 0)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.noContent().build();

    }
}

class ProfessionalService{
    public Professional findById(Long id) {
        return null;
    }

    public List<Professional> getAll() {
        return null;
    }

    public Professional findByFullName(String name) {
        return null;
    }

    public Professional findByEnrolment(String enrolment) {
        return null;
    }

    public Professional findByEmail(String email) {
        return null;
    }

    public Professional findByOnCalls(Long onCalls) {
        return null;
    }


    public Professional create(Professional professional) {
        return null;
    }

    public Professional update(Long id, Professional professional) {
        return null;
    }
}

class Professional{

}

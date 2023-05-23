package com.nocountry.myguard.controllers;

import com.nocountry.myguard.enums.Specialization;
import com.nocountry.myguard.model.Professional;
import com.nocountry.myguard.service.impl.ProfessionalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/professionals")
public class ProfessionalController {
    @Autowired
    private ProfessionalServiceImpl professionalService;

    @GetMapping("")
    public ResponseEntity<List<Professional>> getProfessionals() {
        return ResponseEntity.ok(professionalService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professional> findById(@PathVariable Long id) {
        if (id < 1) return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok(professionalService.findById(id));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("getByParam")
    public ResponseEntity<Professional> findByParam(@RequestParam(required = false) String name,
                                                    @RequestParam(required = false) String enrolment,
                                                    @RequestParam(required = false) String email,
                                                    @RequestParam(required = false) String dni
                                                    //,@RequestParam(required = false) Long onCalls
    ) {

        if (name != null) {
            return this.releaseProfessionalInResponseEntity(professionalService.findByName(name));
        } else if (enrolment != null) {
            return this.releaseProfessionalInResponseEntity(professionalService.findByEnrolment(enrolment));
        } else if (email != null) {
            return this.releaseProfessionalInResponseEntity(professionalService.findByEmail(email));
        } else if (dni != null) {
            return this.releaseProfessionalInResponseEntity(professionalService.findByDni(dni));
        }//else if (onCalls != null) {return this.releaseProfessionalInResponseEntity(professionalService.findByOnCalls(onCalls));}

        return (ResponseEntity<Professional>) ResponseEntity.notFound();
    }

    @PostMapping
    public ResponseEntity<Professional> create(@RequestBody Professional professional) throws Exception {


        return ResponseEntity.ok(professionalService.create(professional));


    }

    @PutMapping
    public ResponseEntity<Professional> update(@RequestParam Long id, @RequestBody Professional professional) {

        if (id < 1 || professional == null) return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok(professionalService.update(id, professional));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Professional> Delete(Long id) {

        if (id < 1) return ResponseEntity.badRequest().build();

        try {
            professionalService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }

    private ResponseEntity<Professional> releaseProfessionalInResponseEntity(Optional<Professional> professionalOptional) {
        if (professionalOptional.isPresent()) {
            return ResponseEntity.ok(professionalOptional.get());
        } else {
            return (ResponseEntity<Professional>) ResponseEntity.notFound();
        }
    }


    private ResponseEntity<Professional> addMonth2Professional(Long idProfessional, Long idMonth) throws Exception {

        if (idMonth == 0 || idProfessional == 0)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(professionalService.addMonth2Professional(idProfessional, idMonth));

    }


    private ResponseEntity<Professional> removeMonth2Professional(Long idProfessional, Long idMonth) throws Exception {

        if (idMonth == 0 || idProfessional == 0)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(professionalService.removeMonth2Professional(idProfessional, idMonth));

    }

    @PostMapping("/{professionalId}/specializations")
    public ResponseEntity<Professional> addSpecialization2Professional(@PathVariable Long professionalId,
                                                                       @RequestBody Specialization specialization) {


        if (professionalId == null || specialization == null) return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok(professionalService.addSpecialization2Professional(professionalId, specialization));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }
}
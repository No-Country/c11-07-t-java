package com.nocountry.myguard.controllers;

import com.nocountry.myguard.model.Month;
import com.nocountry.myguard.model.Professional;
import com.nocountry.myguard.service.impl.MonthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.ServiceNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/months/")
public class MonthController {

    @Autowired
    private MonthServiceImpl monthService;

    @GetMapping("/{id}")
    public ResponseEntity<Month> findById(@PathVariable Long id){

        if (id == 0 || id == null) return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok(monthService.findById(id));
        } catch (ServiceNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("")
    public ResponseEntity<List<Month>> getMonths(){
        return ResponseEntity.ok(monthService.getAll());
    }

    @PostMapping("")
    public ResponseEntity<Month> create(@RequestBody Month month){

        if (month == null ) return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok(monthService.create(month));
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }


    }

    @PutMapping
    public ResponseEntity<Month> update(@RequestParam Long id , @RequestBody Month month){

        if (id == null || id == 0 || month == null) return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok(monthService.update(id, month));
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Month> Delete(Long id) {

        if (id < 1) return ResponseEntity.badRequest().build();

        try {
            monthService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }


}

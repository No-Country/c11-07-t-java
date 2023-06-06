package com.nocountry.myguard.controllers;

import com.nocountry.myguard.model.Month;
import com.nocountry.myguard.service.MonthService;
import com.nocountry.myguard.service.impl.MonthServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.management.ServiceNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/months/")
@CrossOrigin
@Tag(name = "Month" , description = "Month Controller")
@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info(title = "MyGuard API", version = "1.0", description = "Api of hospital on call management"))
public class MonthController {

    @Autowired
    private MonthService monthService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get month by id")
    public ResponseEntity<Month> findById(
            @Parameter(name = "id", description = "Id of the month", required = true)
            @PathVariable Long id){

        if (id == 0 || id == null) return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok(monthService.findById(id));
        } catch (ServiceNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get the list of months")
    public ResponseEntity<List<Month>> getMonths(){
        return ResponseEntity.ok(monthService.getAll());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("specificMonth")
    public ResponseEntity<Month> getMonthByNameAndYear(@RequestParam String name, @RequestParam int year){

        try {
            return ResponseEntity.ok(monthService.findByNameAndYear(name, year));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("")
    @Operation(summary = "create a new month")
    public ResponseEntity<Month> create(
            @Parameter(name = "Month", description = "Body of the month with name, year and type", required = true)
            @RequestBody Month month){

        if (month == null ) return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok(monthService.create(month));
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }


    }


    @PutMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "update a month")
    public ResponseEntity<Month> update(
            @Parameter(name = "id", description = "Id of the month", required = true)
            @RequestParam Long id ,
            @Parameter(name = "Month", description = "Body of the month with name, year and type", required = true)
            @RequestBody Month month){

        if (id == null || id == 0 || month == null) return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok(monthService.update(id, month));
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }




    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "delete a month" , hidden = true)
    public ResponseEntity<Month> Delete(
            @Parameter(name = "id", description = "Id of the month", required = true)
            @RequestParam Long id) {

        if (id < 1) return ResponseEntity.badRequest().build();

        try {
            monthService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }


}

package com.nocountry.myguard.controllers;


import com.nocountry.myguard.model.OnCall;
import com.nocountry.myguard.service.impl.OnCallServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/oncalls/")
@Tag(name = "OnCall" , description = "OnCall Controller")
public class OnCallController {

    private final OnCallServiceImpl onCallService;

    @Autowired
    public OnCallController(OnCallServiceImpl onCallService) {
        this.onCallService = onCallService;
    }


    @PostMapping

    @Operation(summary = "create a new OnCall")
    public ResponseEntity<OnCall> create(
            @Parameter(name = "onCall", description = "new OnCall Body to create", required = true)
            @RequestBody OnCall onCall) {

        if (onCall == null)
            return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok(onCallService.save(onCall));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping

    @Operation(summary = "update an OnCall")
    public ResponseEntity<OnCall> update(
            @Parameter(name = "onCall id", description = "OnCall id to update", required = true)
            @RequestParam Long id,
            @Parameter(name = "onCall", description = "OnCall Body", required = true)
            @RequestBody OnCall onCallUpdate) throws Exception {


        if (id == 0 || onCallUpdate == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(onCallService.update(id, onCallUpdate));
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "get an OnCall by id")
    public ResponseEntity<OnCall> findById(
            @Parameter(name = "onCall id", description = "OnCall id to get", required = true)
            @PathVariable Long id) throws Exception {

        if (id == 0)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(onCallService.findById(id));

    }

    @GetMapping("/getAll")
    @Operation(summary = "get all OnCalls")
    public ResponseEntity<List<OnCall>> findAll() {

        return ResponseEntity.ok(onCallService.findAll());

    }

    @DeleteMapping("/{id}")

    @Operation(summary = "delete an OnCall")
    ResponseEntity<OnCall> Delete(
            @Parameter(name = "onCall id", description = "OnCall id to delete", required = true)
            @RequestParam Long id) {

        if (id == 0)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/getAllByDay/{day}")
    @Operation(summary = "get all OnCalls by day")
    public ResponseEntity<List<OnCall>> findAllOnCallByDay(
            @Parameter(name = "Day number", description = "Get all the OnCalls by day", required = true)
            @PathVariable int day) {

        if (day == 0)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(onCallService.findAllOnCallByDay(day));
    }
}

package com.nocountry.myguard.controllers;


import com.nocountry.myguard.model.OnCall;
import com.nocountry.myguard.service.impl.OnCallServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/oncalls/")
public class OnCallController {

    @Autowired
    private OnCallServiceImpl onCallService;

    @PostMapping
    public ResponseEntity<OnCall> create(@RequestBody OnCall onCall){

        if (onCall == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(onCall);

    }

    @PutMapping
    public ResponseEntity<OnCall> update(@RequestParam Long id , @RequestBody OnCall onCallUpdate) throws Exception{

        if (id == 0 || onCallUpdate == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(onCallService.update(id, onCallUpdate));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<OnCall> findById(@PathVariable Long id) throws Exception {

        if (id == 0)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(onCallService.findById(id));

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OnCall>> findAll(){

        return ResponseEntity.ok(onCallService.findAll());

    }

    @DeleteMapping("/{id}")
    ResponseEntity<OnCall> Delete(Long id){

        if (id == 0)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.noContent().build();

    }
}

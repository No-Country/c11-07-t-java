package com.nocountry.myguard.controllers;


import com.nocountry.myguard.model.OnCall;
import com.nocountry.myguard.service.impl.OnCallServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/oncalls")
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
}

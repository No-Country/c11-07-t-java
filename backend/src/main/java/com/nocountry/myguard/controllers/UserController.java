package com.nocountry.myguard.controllers;

import com.nocountry.myguard.model.User;
import com.nocountry.myguard.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping()
    public ResponseEntity<User> create(@RequestBody User user) throws Exception {

        if (user == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(userService.save(user));

    }

    @PutMapping
    public ResponseEntity<User> update(@RequestParam Long id , @RequestBody User userUpdate) throws Exception{

        if (id == 0 || userUpdate == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(userService.update(id, userUpdate));
    }
}

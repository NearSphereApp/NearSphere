package com.mangalitsa.litsa.controllers;

import com.mangalitsa.litsa.controllers.model.AuthResponse;
import com.mangalitsa.litsa.controllers.model.AuthRequest;
import com.mangalitsa.litsa.controllers.model.NewUserRequest;
import com.mangalitsa.litsa.controllers.model.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @GetMapping
    public UserResponse getUser() {
        return null;// TODO
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest body) {
        return null; // TODO
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse addUser(@RequestBody NewUserRequest body) {
        return null; // TODO
    }

    // TODO: PATCH /{field}
    //       maybe also PUT?
}

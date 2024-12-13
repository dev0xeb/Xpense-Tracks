package org.XpenseTracks.controllers;

import org.XpenseTracks.dtos.request.UserRequest;
import org.XpenseTracks.dtos.response.UserResponse;
import org.XpenseTracks.services.UserServiceImpl;
import org.XpenseTracks.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserServices userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.register(userRequest);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.login(userRequest);
        return ResponseEntity.ok(userResponse);
    }
}

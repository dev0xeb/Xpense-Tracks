package org.XpenseTracks.services;

import org.XpenseTracks.data.model.User;
import org.XpenseTracks.data.repository.UserRepo;
import org.XpenseTracks.dtos.request.UserRequest;
import org.XpenseTracks.dtos.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserServices {
    @Autowired
    private UserRepo userRepo;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(UserRequest userRequest) {
        if(userRequest.getUsername() == null || userRequest.getEmail() == null || userRequest.getPassword() == null) {
            throw new IllegalArgumentException("All fields are required");
        }
        if(!EMAIL_PATTERN.matcher(userRequest.getEmail()).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }
        Optional<User> userEmail = userRepo.findByEmail(userRequest.getEmail());
        Optional<User> userName = userRepo.findByUsername(userRequest.getUsername());
        if (userEmail.isPresent() || userName.isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        User registeredUser = userRepo.save(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(registeredUser.getUsername());
        userResponse.setMessage("Registered Successfully");
        return userResponse;
    }

    @Override
    public UserResponse login(UserRequest userRequest) {
        if(userRequest.getEmail() == null || userRequest.getPassword() == null) {
            throw new IllegalArgumentException("Fill Fields Appropriately");
        }
        if(!EMAIL_PATTERN.matcher(userRequest.getEmail()).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }
        Optional<User> userEmail = userRepo.findByEmail(userRequest.getEmail());
        if(userEmail.isPresent()) {
            User user = userEmail.get();
            if(passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
                UserResponse userResponse = new UserResponse();
                userResponse.setUsername(user.getUsername());
                userResponse.setMessage("Logged in successfully");
                return userResponse;
            } else {
                throw new IllegalArgumentException("Wrong password");
            }
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
}

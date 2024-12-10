package org.XpenseTracks.services;

import org.XpenseTracks.data.repository.UserRepo;
import org.XpenseTracks.dtos.request.UserRequest;
import org.XpenseTracks.dtos.response.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        userRepo.deleteAll();
    }

    @Test
    public void testRegisterUser() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("Big-C");
        userRequest.setEmail("clinton@gmail.com");
        userRequest.setPassword("password");

        UserResponse registeredUser = userService.register(userRequest);
        assertNotNull(registeredUser);
        assertEquals("Big-C", userRequest.getUsername());
        assertEquals("clinton@gmail.com", userRequest.getEmail());
    }

    @Test
    public void testRegisterALreadyRegisteredUser() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("Big-C");
        userRequest.setEmail("clinton@gmail.com");
        userRequest.setPassword("password");

        UserResponse registeredUser = userService.register(userRequest);
        Exception registerUserAgainException = assertThrows(IllegalArgumentException.class, () -> {
            userService.register(userRequest);
        });
        String exceptionMessage = "Username already exists";
        String actualExceptionMessage = registerUserAgainException.getMessage();

        assertEquals(exceptionMessage, actualExceptionMessage);
    }

    @Test
    public void testRegisterWithInvalidEmail() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("Big-C");
        userRequest.setEmail("clintongmailcom");
        userRequest.setPassword("password");
        Exception invalidEmailException = assertThrows(IllegalArgumentException.class, () -> {
            userService.register(userRequest);
        });
        String exceptionMessage = "Invalid email format";
        String actualExceptionMessage = invalidEmailException.getMessage();

        assertEquals(exceptionMessage, actualExceptionMessage);
    }

    @Test
    public void testRegisterWithMissingFields() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("Big-C");
        userRequest.setEmail(null);
        userRequest.setPassword("password");
        Exception missingFieldsException = assertThrows(IllegalArgumentException.class, () -> {
            userService.register(userRequest);
        });
        String exceptionMessage = "All fields are required";
        String actualExceptionMessage = missingFieldsException.getMessage();

        assertEquals(exceptionMessage, actualExceptionMessage);
    }

    @Test
    public void testLoginWithValidCredentials(){
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("Big-C");
        userRequest.setEmail("clinton@gmail.com");
        userRequest.setPassword("password");
        userService.register(userRequest);

        UserRequest userLogin = new UserRequest();
        userLogin.setEmail("clinton@gmail.com");
        userLogin.setPassword("password");

        UserResponse loginUser = userService.login(userLogin);
        assertNotNull(loginUser);
        assertEquals("Big-C", loginUser.getUsername());
    }

    @Test
    public void testLoginWithInvalidCredentials(){
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("Big-C");
        userRequest.setEmail("clinton@gmail.com");
        userRequest.setPassword("password");
        userService.register(userRequest);

        UserRequest userLogin = new UserRequest();
        userLogin.setEmail("clinton@gmail.com");
        userLogin.setPassword("wrongpassword");

        Exception invalidPasswordException = assertThrows(IllegalArgumentException.class, () -> {
            userService.login(userLogin);
        });
        String exceptionMessage = "Wrong password";
        String actualExceptionMessage = invalidPasswordException.getMessage();

        assertEquals(exceptionMessage, actualExceptionMessage);
    }

    @Test
    public void testLoginNonExistentUser(){
        UserRequest userLogin = new UserRequest();
        userLogin.setEmail("Big-C@GMAIL.com");
        userLogin.setPassword("password");

        Exception NonExistentUserException = assertThrows(IllegalArgumentException.class, () -> {
            userService.login(userLogin);
        });
        String exceptionMessage = "User not found";
        String actualExceptionMessage = NonExistentUserException.getMessage();

        assertEquals(exceptionMessage, actualExceptionMessage);
    }

    @Test
    public void testLoginWithInvalidEmail(){
        UserRequest userLogin = new UserRequest();
        userLogin.setEmail("Big-Cmail.com");
        userLogin.setPassword("password");
        Exception invalidEmailException = assertThrows(IllegalArgumentException.class, () -> {
            userService.login(userLogin);
        });
        String exceptionMessage = "Invalid email format";
        String actualExceptionMessage = invalidEmailException.getMessage();

        assertEquals(exceptionMessage, actualExceptionMessage);
    }

    @Test
    public void testLoginWithEmptyCredentials(){
        UserRequest userLogin = new UserRequest();
        userLogin.setEmail("");
        userLogin.setEmail("");
        Exception emptyCredentialsException = assertThrows(IllegalArgumentException.class, () -> {
            userService.login(userLogin);
        });
        String exceptionMessage = "Fill Fields Appropriately";
        String actualExceptionMessage = emptyCredentialsException.getMessage();

        assertEquals(exceptionMessage, actualExceptionMessage);
    }
}
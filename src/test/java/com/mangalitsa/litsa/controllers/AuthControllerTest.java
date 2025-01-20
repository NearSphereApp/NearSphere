package com.mangalitsa.litsa.controllers;

import com.mangalitsa.litsa.controllers.model.AuthRequest;
import com.mangalitsa.litsa.controllers.model.AuthResponse;
import com.mangalitsa.litsa.controllers.model.NewUserRequest;
import com.mangalitsa.litsa.services.UserService;
import com.mangalitsa.litsa.services.auth.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    UserService userService;

    @Mock
    AuthenticationService authenticationService;

    @InjectMocks
    AuthController authController;

    @BeforeEach
    void setUp() {
        authController = new AuthController();
        authController.userService = userService;
        authController.authenticationService = authenticationService;
    }

    @Test
    void signUp() throws NoSuchAlgorithmException, InvalidKeySpecException {
        //Arrange
        NewUserRequest newUserRequest = new NewUserRequest("some@example.com", "securePassword", "Bob");
        doNothing().when(userService).signUp(newUserRequest);

        //Act
        ResponseEntity<Void> result = authController.signUp(newUserRequest);

        //Assert
        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
    }

    @Test
    void login() {
        //Arrange
        AuthRequest request = new AuthRequest("some@example.com", "securePassword");
        AuthResponse response = new AuthResponse("asdf", 2L);
        when(authenticationService.authenticate(request)).thenReturn(response);

        //Act
        ResponseEntity<AuthResponse> result = authController.login(request);

        //Assert
        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(result.getBody()).isEqualTo(response);
    }
}
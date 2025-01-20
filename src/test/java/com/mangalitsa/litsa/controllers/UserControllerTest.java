package com.mangalitsa.litsa.controllers;

import com.mangalitsa.litsa.controllers.model.ChangeUserInfoRequest;
import com.mangalitsa.litsa.controllers.model.UserResponse;
import com.mangalitsa.litsa.model.Password;
import com.mangalitsa.litsa.model.User;

import com.mangalitsa.litsa.services.UserServiceImpl;
import com.mangalitsa.litsa.services.auth.CustomUserDetails;
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
class UserControllerTest {

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getUser() {
        //Arrange
        UserResponse userResponse = new UserResponse(1, "email@email.com" , "sample_user");

        User user = new User();
        user.setEmail("email@email.com");

        Password password = new Password();
        password.setPasswordHash("asdf");

        CustomUserDetails userDetails1 = new CustomUserDetails(user , password);

        when(userService.getUserByEmail(user.getEmail())).thenReturn(userResponse);
        //Act
        ResponseEntity<UserResponse> result = userController.getUser(userDetails1);
        //Assert
        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(result.getBody()).isEqualTo(userResponse);
    }


    @Test
    void updateUserInfo() throws NoSuchAlgorithmException, InvalidKeySpecException {
        //Arrange
        ChangeUserInfoRequest request = new ChangeUserInfoRequest("User_Name" , "password");

        doNothing().when(userService).updateInfo(1L, request);
        //Act
        ResponseEntity<Void> result = userController.updateUserInfo(1L, request);
        //Assert
        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
    }
}
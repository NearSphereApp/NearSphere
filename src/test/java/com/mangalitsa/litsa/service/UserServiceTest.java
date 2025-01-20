package com.mangalitsa.litsa.service;

import com.mangalitsa.litsa.controllers.model.NewUserRequest;
import com.mangalitsa.litsa.controllers.model.UserResponse;
import com.mangalitsa.litsa.model.Password;
import com.mangalitsa.litsa.model.User;
import com.mangalitsa.litsa.repositories.PasswordRepository;
import com.mangalitsa.litsa.repositories.UserRepository;
import com.mangalitsa.litsa.services.PasswordService;
import com.mangalitsa.litsa.services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordRepository passwordRepository;

    @Mock
    PasswordService passwordService;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getUserByEmail(){
        //Arrange
        UserResponse response = new UserResponse(1L , "email@email.com" , "display_name");
        User user = new User();
        user.setEmail(response.email());
        user.setDisplayName(response.displayName());
        user.setId(response.id());
        when(userRepository.findByEmail(response.email())).thenReturn(Optional.of(user));
        //Act
        UserResponse result = userService.getUserByEmail(response.email());
        //Assert
        assertThat(result).isEqualTo(response);
    }

    @Test
    void signup() throws NoSuchAlgorithmException, InvalidKeySpecException {
        //Arrange
        NewUserRequest request = new NewUserRequest("email@email.com"  , "password", "display_name");

        User user = new User();
        user.setEmail(request.email());
        user.setDisplayName(request.displayName());

        Password password = new Password();
        password.setPasswordHash(request.password());

        when(passwordService.hashPassword(request.password())).thenReturn("hashed password");
        when(passwordRepository.save(any())).thenReturn(password);
        when(userRepository.save(any())).thenReturn(user);

        //Act
        userService.signUp(request);

        //Assert
        verify(userRepository).save(any());
        verify(passwordRepository).save(any());
        verify(passwordService).hashPassword(any());

    }
}

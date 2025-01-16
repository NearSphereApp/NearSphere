package com.mangalitsa.litsa.controllers;

import com.mangalitsa.litsa.controllers.model.ConfirmPasswordResetRequest;
import com.mangalitsa.litsa.controllers.model.PasswordResetRequest;

import com.mangalitsa.litsa.services.PasswordResetTokenService;
import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/password")
public class PasswordController {

    @Autowired
    PasswordResetTokenService passwordResetTokenService;


    @PostMapping("/request-password-reset")
    public ResponseEntity<Void> requestPasswordReset(@RequestBody PasswordResetRequest request) throws MessagingException {
        passwordResetTokenService.resetPassword(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestBody ConfirmPasswordResetRequest request){
        passwordResetTokenService.confirmResetPassword(request);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/reset-password-form")
    public String showResetPasswordForm(@RequestParam String email,
                                        @RequestParam UUID token,
                                        Model model) {
        // Add necessary attributes for the form, e.g., token and email.
        model.addAttribute("email", email);
        model.addAttribute("token", token.toString());
        return "reset-password";  // This corresponds to reset-password.html in your templates folder.
    }

}

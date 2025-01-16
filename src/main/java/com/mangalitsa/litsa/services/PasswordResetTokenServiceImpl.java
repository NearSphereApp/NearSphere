package com.mangalitsa.litsa.services;

import com.mangalitsa.litsa.controllers.model.ConfirmPasswordResetRequest;
import com.mangalitsa.litsa.controllers.model.PasswordResetRequest;
import com.mangalitsa.litsa.model.Password;
import com.mangalitsa.litsa.model.PasswordResetToken;
import com.mangalitsa.litsa.model.User;
import com.mangalitsa.litsa.repositories.PasswordRepository;
import com.mangalitsa.litsa.repositories.PasswordResetTokenRepository;
import com.mangalitsa.litsa.repositories.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordRepository passwordRepository;

    @Autowired
    EmailSender emailSender;

    @Override
    public void resetPassword(PasswordResetRequest request) throws MessagingException {
        Optional<User> optionalUser = userRepository.findByEmail(request.email());

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User could not find!");
        } else {
            User user = optionalUser.get();
            UUID randomToken = UUID.randomUUID();
            PasswordResetToken passwordResetToken = new PasswordResetToken();
            passwordResetToken.setResetToken(randomToken);
            passwordResetToken.setUser(user);
            passwordResetToken.setExpiresAt(LocalDateTime.now().plusMinutes(15));
            passwordResetTokenRepository.save(passwordResetToken);

            emailSender.sendEmail(randomToken, user.getEmail());
        }
    }

    @Override
    public void confirmResetPassword(ConfirmPasswordResetRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        Optional<PasswordResetToken> optionalToken = passwordResetTokenRepository.findByUser(user);

        if (optionalToken.isEmpty()) {
            throw new RuntimeException("Token not found!");
        } else {
            PasswordResetToken passwordResetToken = optionalToken.get();

            // Additional validations:
            // 1. Check for expiration
            // 2. Ensure token has not been used previously

            if (passwordResetToken.getResetToken().equals(request.token())) {
                // For security, consider validating expiration and whether the token has already been used
                if (passwordResetToken.getExpiresAt().isBefore(LocalDateTime.now())) {
                    throw new RuntimeException("Token expired!");
                }
                if (passwordResetToken.getUsed()) {
                    throw new RuntimeException("Token already used!");
                }

                Password userPassword = passwordRepository.findByUser(user);
                // Todo: perform proper password hashing/encoding before saving!
                userPassword.setPasswordHash(request.password());
                passwordRepository.save(userPassword);

                // Mark token as used
                passwordResetToken.setUsed(true);
                passwordResetTokenRepository.save(passwordResetToken);
            }
        }
    }
}

package com.mangalitsa.litsa.repositories;

import com.mangalitsa.litsa.model.PasswordResetToken;
import com.mangalitsa.litsa.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByUser(User user);

}

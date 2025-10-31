package com.example.backend.core.repositorys;

import com.example.backend.core.models.UserVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserVerificationRepository extends JpaRepository<UserVerification, Long> {
    Optional<UserVerification> findByEmail(String email);
    Optional<UserVerification> findById(Long id);
}
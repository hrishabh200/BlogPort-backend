package com.programming.blogport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.programming.blogport.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
}

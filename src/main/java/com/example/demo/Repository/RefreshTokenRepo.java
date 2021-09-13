package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Entity.RefreshToken;
@Repository
public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long> 
{
    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
}


package com.example.securityApp.repository;

import com.example.securityApp.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaTokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findTokenByIdentifier(String identifier);
}

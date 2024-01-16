package com.tbs.personnel.deployment.tracker.repository;


import com.tbs.personnel.deployment.tracker.model.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, String> {


  Token findByToken(String token);

  void deleteByToken(String token);
}

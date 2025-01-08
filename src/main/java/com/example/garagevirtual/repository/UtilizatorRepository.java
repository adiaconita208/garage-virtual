package com.example.garagevirtual.repository;

import com.example.garagevirtual.model.Utilizator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilizatorRepository extends JpaRepository<Utilizator, Long> {
    Optional<Utilizator> findByEmail(String email);
}
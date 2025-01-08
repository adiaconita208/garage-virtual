package com.example.garagevirtual.repository;

import com.example.garagevirtual.model.Utilizator;
import com.example.garagevirtual.model.Vehicul;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehiculRepository extends JpaRepository<Vehicul, Long> {
    List<Vehicul> findByProprietar(Utilizator proprietar);
}

package com.example.garagevirtual.repository;

import com.example.garagevirtual.model.Vehicul;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicul, Long> {
}

package com.example.garagevirtual.service;

import com.example.garagevirtual.model.Utilizator;
import com.example.garagevirtual.repository.UtilizatorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilizatorService {
    private final UtilizatorRepository utilizatorRepository;

    public UtilizatorService(UtilizatorRepository utilizatorRepository) {
        this.utilizatorRepository = utilizatorRepository;
    }

    public Optional<Utilizator> findUtilizatorByEmail(String email) {
        return utilizatorRepository.findByEmail(email);
    }

    public Utilizator saveUtilizator(Utilizator user) {
        return utilizatorRepository.save(user);
    }

    public void deleteUtilizatorById(Long id) {
        if (!utilizatorRepository.existsById(id)) {
            throw new RuntimeException("Utilizatorul nu exista");
        }

        utilizatorRepository.deleteById(id);
    }
}
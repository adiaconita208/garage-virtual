package com.example.garagevirtual.controller;

import com.example.garagevirtual.model.Utilizator;
import com.example.garagevirtual.service.UtilizatorService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    private final UtilizatorService utilizatorService;

    public LoginController(UtilizatorService utilizatorService) {
        this.utilizatorService = utilizatorService;
    }

    @PostMapping("/register")
    public Utilizator registerUtilizator(@RequestBody Utilizator utilizator) {
        return utilizatorService.saveUtilizator(utilizator);
    }

    @PostMapping("/login")
    public String loginUtilizator(@RequestParam String email, @RequestParam String parola) {
        Optional<Utilizator> utilizator = utilizatorService.findUtilizatorByEmail(email);
        if (utilizator.isPresent() && utilizator.get().getParola().equals(parola)) {
            return "Login successful!";
        }
        return "Invalid email or password.";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUtilizator(@PathVariable Long id) {
        utilizatorService.deleteUtilizatorById(id);
        return "Utilizatorul cu ID-ul " + id + " a fost sters.";
    }
}
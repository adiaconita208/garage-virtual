package com.example.garagevirtual.controller;

import com.example.garagevirtual.model.Utilizator;
import com.example.garagevirtual.service.SessionService;
import com.example.garagevirtual.service.UtilizatorService;
import com.example.garagevirtual.service.VehiculService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UtilizatorService utilizatorService;
    @Autowired
    public final SessionService sessionService;

    public LoginController(SessionService sessionService) {
        this.sessionService = sessionService;
    }


    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("error", "");
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String parola, Model model) {
        Optional<Utilizator> user = utilizatorService.findUtilizatorByEmail(email);
        if (user.isPresent() && user.get().getParola().equals(parola)) {
            sessionService.setCurrentUser(user.get());
            return "redirect:/acasa";
        }
        model.addAttribute("error", "Email si parola invalide.");
        return "login";
    }
}
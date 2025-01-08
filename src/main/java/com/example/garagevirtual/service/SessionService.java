package com.example.garagevirtual.service;

import com.example.garagevirtual.model.Utilizator;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private Utilizator currentUser;

    public Utilizator getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Utilizator currentUser) {
        this.currentUser = currentUser;
    }
}
package dev.jonkursani.restapigr1.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    UserDetails authenticate(String email, String password);
    String generateToken(UserDetails user);
    UserDetails validateToken(String token);
}
package dev.jonkursani.restapigr1.services.impls;

import dev.jonkursani.restapigr1.services.AuthService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public UserDetails authenticate(String email, String password) {
        return null;
    }

    @Override
    public String generateToken(UserDetails user) {
        return "";
    }
}
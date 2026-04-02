package dev.jonkursani.restapigr1.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @PreAuthorize("hasAuthority('admin:read')")
    @GetMapping
    public String get() {
        return "GET::ADMIN";
    }

    @PreAuthorize("hasAuthority('admin:write')")
    @PostMapping
    public String post() {
        return "POST::ADMIN";
    }

    @PreAuthorize("hasAuthority('admin:write')")
    @PutMapping
    public String put() {
        return "PUT::ADMIN";
    }

    @PreAuthorize("hasAuthority('admin:write')")
    @DeleteMapping
    public String delete() {
        return "DELETE::ADMIN";
    }
}
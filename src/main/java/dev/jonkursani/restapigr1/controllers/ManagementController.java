package dev.jonkursani.restapigr1.controllers;

import dev.jonkursani.restapigr1.annotations.ManagerRead;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/management")
public class ManagementController {
//    @PreAuthorize("hasAuthority('manager:read')")
    @ManagerRead
    @GetMapping
    public String get() {
        return "GET::MANAGEMENT";
    }

    @PreAuthorize("hasAuthority('manager:write')")
    @PostMapping
    public String post() {
        return "POST::MANAGEMENT";
    }

    @PreAuthorize("hasAuthority('manager:write')")
    @PutMapping
    public String put() {
        return "PUT::MANAGEMENT";
    }

    @PreAuthorize("hasAuthority('manager:write')")
    @DeleteMapping
    public String delete() {
        return "DELETE::MANAGEMENT";
    }
}
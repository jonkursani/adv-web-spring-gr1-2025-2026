package dev.jonkursani.restapigr1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// flyway migration gjeneron skripten e SQL prej modelit (klases se javes => entiteti)
// entity attributes from DB => prej kolones ne db e gjeneron propertine (field) ne klasen specifike
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "email", length = 80, nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_by")
    private Integer createdBy;

    @CreationTimestamp // autofill with current timestamp
    @Column(name = "created_at", updatable = false)
    public LocalDateTime createdAt;

    @UpdateTimestamp // autofill with current timestamp
    @Column(name = "updated_at")
    public LocalDateTime updatedAt;
}
package dev.jonkursani.restapigr1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_by")
    private Integer createdBy;
}
package dev.jonkursani.restapigr1.configs;

import dev.jonkursani.restapigr1.entities.Role;
import dev.jonkursani.restapigr1.entities.User;
import dev.jonkursani.restapigr1.repositories.UserRepository;
import dev.jonkursani.restapigr1.security.AppUserDetailsService;
import dev.jonkursani.restapigr1.security.JwtAuthenticationFilter;
import dev.jonkursani.restapigr1.services.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthService authService) {
        return new JwtAuthenticationFilter(authService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtAuthenticationFilter jwtAuthenticationFilter
    ) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/api/departments/**").permitAll()
                .requestMatchers(
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/docs",
                        "/api/auth/**"
                ).permitAll()

                // hasRole() per 1 rol, hasAnyRole() per ma shume se 1 rol
                // hasAuthority() per 1 permission, hasAnyAuthority() per ma shume se 1 permission
//                .requestMatchers("/api/management/**").hasAnyRole(Role.MANAGER.name(), Role.ADMIN.name())
//                .requestMatchers(HttpMethod.GET, "/api/management/**")
//                    .hasAnyAuthority(Permission.MANAGER_READ.getPermission(), Permission.ADMIN_READ.getPermission())
//                .requestMatchers(HttpMethod.POST, "/api/management/**")
//                    .hasAnyAuthority(Permission.MANAGER_WRITE.getPermission(), Permission.ADMIN_WRITE.getPermission())
//                .requestMatchers(HttpMethod.PUT, "/api/management/**")
//                    .hasAnyAuthority(Permission.MANAGER_WRITE.getPermission(), Permission.ADMIN_WRITE.getPermission())
//                .requestMatchers(HttpMethod.DELETE, "/api/management/**")
//                    .hasAnyAuthority(Permission.MANAGER_WRITE.getPermission(), Permission.ADMIN_WRITE.getPermission())

                .anyRequest().authenticated()
        );

        // CSRF disabled
        http.csrf(csrf -> csrf.disable());

        http.sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        // Filteri qe e kemi kriju
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        AppUserDetailsService userDetailsService = new AppUserDetailsService(userRepository);

        String email = "user@test.com";

        userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User user = User.builder()
                            .name("User")
                            .email(email)
                            .password(passwordEncoder().encode("Test#123"))
                            .isActive(true)
                            .role(Role.ADMIN)
                            .createdBy(1)
                            .build();

                    return userRepository.save(user);
                });

        return userDetailsService;
    }
}
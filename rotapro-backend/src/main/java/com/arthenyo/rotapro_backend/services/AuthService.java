package com.arthenyo.rotapro_backend.services;


import com.arthenyo.rotapro_backend.model.model_postgresql.UserPostgresql;
import com.arthenyo.rotapro_backend.repositories.repository_postgresql.PasswordRecoverPostegresqlRepository;
import com.arthenyo.rotapro_backend.repositories.repository_postgresql.UserPostgresqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserPostgresqlRepository userRepository;
    @Autowired
    private PasswordRecoverPostegresqlRepository passwordRecoverRepository;

    protected UserPostgresql authenticated() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String username = jwtPrincipal.getClaim("username");
            return userRepository.findByEmail(username);
        }
        catch (Exception e) {
            throw new UsernameNotFoundException("Invalid user");
        }
    }

}

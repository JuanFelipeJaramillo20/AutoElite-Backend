package com.autoelite.AutoElite.Usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioDAO extends JpaRepository<Usuario, String> {
    UserDetails findByEmail(String email);
}


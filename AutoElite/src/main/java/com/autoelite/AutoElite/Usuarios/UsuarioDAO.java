package com.autoelite.AutoElite.Usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UsuarioDAO extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByEmail(String email);

    //@Query("update usuario c set c.token = :token where c.email = :email")
    //void upadateToken(String token, String email);
}


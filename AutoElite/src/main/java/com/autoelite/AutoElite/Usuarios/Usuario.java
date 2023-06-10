package com.autoelite.AutoElite.Usuarios;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails{

    @Id
    private String id;
    private String nombres;
    private String email;
    private String contrasena;
    private String telefono;
    @Enumerated(EnumType.STRING)
    private RolUsuario rolUsuario;
    private boolean bloqueado;
    private boolean isEnabled = false;

    public Usuario(String id, String nombres, String email, String telefono, String contrasena, RolUsuario rol) {
        this.id =id;
        this.nombres = nombres;
        this.email = email;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.rolUsuario = rol;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  List.of(new SimpleGrantedAuthority(rolUsuario.name()));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername(){
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}

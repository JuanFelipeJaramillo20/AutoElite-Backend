package com.autoelite.AutoElite.Usuarios;

import com.autoelite.AutoElite.Publicacion.Publicacion;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombres;
    @Column(unique = true)
    private String email;
    private String contrasena;
    private String telefono;
    @Enumerated(EnumType.STRING)
    private RolUsuario rolUsuario;
    @Column(columnDefinition = "TEXT")
    private String token;

    private boolean bloqueado;
    private boolean isEnabled = true;

    @Column(name = "imagen", columnDefinition = "bytea")
    private Byte[] imagenPerfil;

    @ManyToMany
    @JoinTable(
            name = "usuario_publicacion",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "publicacion_id")
    )
    private List<Publicacion> publicacionesFavoritas;

    public Usuario(String token) {
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rolUsuario.name()));
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

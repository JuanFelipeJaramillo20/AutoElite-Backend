package com.autoelite.AutoElite.Usuarios;

import com.autoelite.AutoElite.Publicacion.Publicacion;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombres;
    @Column(unique = true)
    private String email;
    private String contrasena;
    private String telefono;
    @Enumerated(EnumType.STRING)
    private RolUsuario rolUsuario;

    private boolean bloqueado;
    private boolean isEnabled = true;

    private String imagenPerfil;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_publicacion",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "publicacion_id")
    )
    private List<Publicacion> publicacionesFavoritas;

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

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombres='" + nombres + '\'' +
                ", email='" + email + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", telefono='" + telefono + '\'' +
                ", rolUsuario=" + rolUsuario +
                ", bloqueado=" + bloqueado +
                ", isEnabled=" + isEnabled +
                ", imagenPerfil=" + imagenPerfil +
                '}';
    }
}

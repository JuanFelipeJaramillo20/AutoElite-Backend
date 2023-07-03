package com.autoelite.AutoElite.Publicacion;

import com.autoelite.AutoElite.Carro.Carro;
import com.autoelite.AutoElite.Usuarios.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Publicacion {

    @Id
    private String id;
    private String fechaPublicacion;
    private String ciudad;

    @ManyToOne
    @JoinColumn(name = "usuario_email", referencedColumnName = "email")
    @JsonIgnoreProperties({"contrasena", "bloqueado", "isEnabled", "enabled", "password",
            "credentialsNonExpired", "accountNonExpired", "authorities", "username", "accountNonLocked",
            "publicacionesFavoritas"})
    private Usuario usuarioPublicacion;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Carro carroPublicacion;

    private String descripcion;
}

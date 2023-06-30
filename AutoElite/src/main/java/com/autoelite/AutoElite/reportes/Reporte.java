package com.autoelite.AutoElite.reportes;

import com.autoelite.AutoElite.Publicacion.Publicacion;
import com.autoelite.AutoElite.Usuarios.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comentarios;
    @JsonIgnoreProperties({"contrasena","bloqueado","isEnabled","enabled","password",
            "credentialsNonExpired","accountNonExpired","authorities","username","accountNonLocked",
            "publicacionesFavoritas"})
    private Usuario usuarioReporta;

    @ManyToOne
    @JoinColumn(name = "publicacion_id", referencedColumnName = "id", nullable = false)
    private Publicacion reportePublicacion;
}

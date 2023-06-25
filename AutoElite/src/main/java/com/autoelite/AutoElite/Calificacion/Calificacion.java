package com.autoelite.AutoElite.Calificacion;

import com.autoelite.AutoElite.Usuarios.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receiver", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties({"contrasena","bloqueado","isEnabled","enabled","password",
            "credentialsNonExpired","accountNonExpired","authorities","username","accountNonLocked",
            "publicacionesFavoritas"})
    private Usuario receiver;

    @ManyToOne
    @JoinColumn(name = "sender", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties({"contrasena","bloqueado","isEnabled","enabled","password",
            "credentialsNonExpired","accountNonExpired","authorities","username","accountNonLocked",
            "publicacionesFavoritas"})
    private Usuario sender;

    private Date fecha;
    private String comentarios;
    private int numEstrellas;
}

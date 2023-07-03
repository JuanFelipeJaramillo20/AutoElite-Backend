package com.autoelite.AutoElite.mensajeVendedor;

import com.autoelite.AutoElite.Usuarios.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MensajeVendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String telefono;
    private String email;
    private String mensaje;
    private String asunto;

    @ManyToOne
    @JsonIgnoreProperties({"contrasena","bloqueado","isEnabled","enabled","password",
            "credentialsNonExpired","accountNonExpired","authorities","username","accountNonLocked",
            "publicacionesFavoritas"})
    private Usuario sender;

    @ManyToOne
    @JsonIgnoreProperties({"contrasena","bloqueado","isEnabled","enabled","password",
            "credentialsNonExpired","accountNonExpired","authorities","username","accountNonLocked",
            "publicacionesFavoritas"})
    private Usuario receiver;
    private Date fecha;

}

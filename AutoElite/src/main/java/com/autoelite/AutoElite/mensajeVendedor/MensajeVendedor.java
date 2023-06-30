package com.autoelite.AutoElite.mensajeVendedor;

import com.autoelite.AutoElite.Usuarios.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private long id;
    private String telefono;
    private String email;
    private String mensaje;
    @JsonIgnoreProperties({"contrasena","bloqueado","isEnabled","enabled","password",
            "credentialsNonExpired","accountNonExpired","authorities","username","accountNonLocked",
            "publicacionesFavoritas"})
    private Usuario sender;
    @JsonIgnoreProperties({"contrasena","bloqueado","isEnabled","enabled","password",
            "credentialsNonExpired","accountNonExpired","authorities","username","accountNonLocked",
            "publicacionesFavoritas"})
    private Usuario receiver;
    private Date fecha;

}

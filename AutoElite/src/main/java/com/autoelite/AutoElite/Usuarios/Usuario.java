package com.autoelite.AutoElite.Usuarios;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    private String id;
    private String nombres;
    private String email;
    private String contrasena;
    private String telefono;
    private Byte rol;

}

package com.autoelite.AutoElite.Usuarios;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@Entity
public class Usuario {

    @Id
    private String id;
    private String nombres;
    private String email;
    private String contrasena;
    private String telefono;
    private Byte rol;
    public Usuario(String nombres, String email, String contrasena, String telefono, String id, Byte rol) {
        this.nombres = nombres;
        this.email = email;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.id = id;
        this.rol = rol;
    }

    public Usuario() {
    }

}

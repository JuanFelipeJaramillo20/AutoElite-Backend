package com.autoelite.AutoElite;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Usuario {

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

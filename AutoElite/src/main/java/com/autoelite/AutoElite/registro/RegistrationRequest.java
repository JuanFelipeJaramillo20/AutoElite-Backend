package com.autoelite.AutoElite.registro;

import com.autoelite.AutoElite.Usuarios.RolUsuario;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private String nombres;
    private String email;
    private String telefono;
    private String contrasena;
    private RolUsuario rolUsuario;
}

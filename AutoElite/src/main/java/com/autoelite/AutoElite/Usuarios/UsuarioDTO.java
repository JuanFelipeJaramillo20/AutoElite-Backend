package com.autoelite.AutoElite.Usuarios;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String nombres;
    private String email;
    private String telefono;
}

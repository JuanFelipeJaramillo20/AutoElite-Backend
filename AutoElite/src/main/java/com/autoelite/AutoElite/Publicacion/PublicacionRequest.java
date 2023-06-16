package com.autoelite.AutoElite.Publicacion;

import com.autoelite.AutoElite.Carro.CarroRequest;
import com.autoelite.AutoElite.Usuarios.Usuario;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class PublicacionRequest {
    private Long id;
    private String fechaPublicacion;
    private String ciudad;
    private String usuarioId;
    private CarroRequest carro;
    private String descripcion;

    // Constructors, getters, and setters
}


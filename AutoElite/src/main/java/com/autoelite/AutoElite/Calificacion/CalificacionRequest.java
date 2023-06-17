package com.autoelite.AutoElite.Calificacion;


import com.autoelite.AutoElite.Usuarios.Usuario;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CalificacionRequest {

    private String receiver;
    private String sender;
    private String comentarios;
    private int numEstrellas;

}

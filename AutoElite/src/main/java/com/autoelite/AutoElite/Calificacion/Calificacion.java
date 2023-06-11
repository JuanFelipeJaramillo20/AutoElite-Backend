package com.autoelite.AutoElite.Calificacion;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Calificacion {

    @Id
    private Long id;
    private String fecha;
    private String comentarios;
    private int numEstrellas;
}

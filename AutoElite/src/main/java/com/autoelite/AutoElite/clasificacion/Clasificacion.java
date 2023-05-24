package com.autoelite.AutoElite.clasificacion;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Clasificacion {

    @Id
    private Long id;
    private String fecha;
    private String comentarios;
    private int numEstrellas;
}

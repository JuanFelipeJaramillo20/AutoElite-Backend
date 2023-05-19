package com.autoelite.AutoElite;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "clasificacion")
@Entity(name = "Clasificaion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Clasificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fecha;
    private String comentarios;
    private int numEstrellas;
}

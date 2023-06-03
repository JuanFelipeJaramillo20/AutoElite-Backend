package com.autoelite.AutoElite.Carro;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Carro {
    @Id
    private Long id;
    private String puertas;
    private String motor;
    private String ciudad;
    private String matricula;
    private String marca;
    private String placa;
    private String color;
    private String tipo;
    private String combustible;
    private String year;
    private String estado;

}

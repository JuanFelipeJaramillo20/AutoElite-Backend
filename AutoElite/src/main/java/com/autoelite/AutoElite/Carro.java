package com.autoelite.AutoElite;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "carro")
@Entity(name = "Carro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

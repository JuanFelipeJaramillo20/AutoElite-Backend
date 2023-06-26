package com.autoelite.AutoElite.Carro;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class CarroRequest {
    private String puertas;
    private String motor;
    private String ciudad;
    private String marca;
    private String placa;
    private String color;
    private String tipo;
    private String combustible;
    private String year;
    private String estado;
    private String transmision;
    private Double precio;
    private Integer kilometraje;
    private Boolean precioEsNegociable;

    private List<String> imagenes;

    // Constructors, getters, and setters
}

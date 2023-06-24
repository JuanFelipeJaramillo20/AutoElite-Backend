package com.autoelite.AutoElite.Carro;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Carro {

    private String puertas;
    private String motor;
    private String ciudad;
    private String marca;
    @Id
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

    @ElementCollection
    private List<String> imagenes;
}

package com.autoelite.AutoElite.Publicacion;

import com.autoelite.AutoElite.Carro.Carro;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Publicacion {

    @Id
    private Long id;
    private String fechaPublicacion;
    private String ciudad;

    @OneToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Carro carroPublicacion;
}

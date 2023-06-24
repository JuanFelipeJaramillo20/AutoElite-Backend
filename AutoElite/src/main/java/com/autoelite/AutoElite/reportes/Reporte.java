package com.autoelite.AutoElite.reportes;

import com.autoelite.AutoElite.Publicacion.Publicacion;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comentarios;

    @ManyToOne
    @JoinColumn(name = "publicacion_id", referencedColumnName = "id", nullable = false)
    private Publicacion reportePublicacion;
}

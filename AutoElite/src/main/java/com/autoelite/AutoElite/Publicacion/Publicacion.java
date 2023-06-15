package com.autoelite.AutoElite.Publicacion;

import com.autoelite.AutoElite.Carro.Carro;
import com.autoelite.AutoElite.Usuarios.Usuario;
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

    @ManyToOne
    @JoinColumn(name = "usuario_email", referencedColumnName = "email", nullable = false)
    private Usuario usuarioPublicacion;

    @OneToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Carro carroPublicacion;

    private String descripcion;
}

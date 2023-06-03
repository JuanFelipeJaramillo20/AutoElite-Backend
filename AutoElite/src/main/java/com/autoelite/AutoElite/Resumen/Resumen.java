package com.autoelite.AutoElite.Resumen;

import com.autoelite.AutoElite.Carro.Carro;
import com.autoelite.AutoElite.Publicacion.Publicacion;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Resumen {

    private long idPublicacion;
    private Carro carroPublicacion;
}

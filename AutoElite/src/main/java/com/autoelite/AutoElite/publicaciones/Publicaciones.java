package com.autoelite.AutoElite.publicaciones;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Publicaciones {

    @Id
    private Long id;
    private String fechaPublicacion;
    private String ciudad;

}

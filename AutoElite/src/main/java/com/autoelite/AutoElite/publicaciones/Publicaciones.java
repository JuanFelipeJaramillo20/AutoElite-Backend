package com.autoelite.AutoElite.publicaciones;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "publicaciones")
@Entity(name = "Publicaciones")
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

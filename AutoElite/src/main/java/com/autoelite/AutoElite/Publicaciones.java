package com.autoelite.AutoElite;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "publicaciones")
@Entity(name = "Publicaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Publicaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fechaPublicacion;
    private String ciudad;

}

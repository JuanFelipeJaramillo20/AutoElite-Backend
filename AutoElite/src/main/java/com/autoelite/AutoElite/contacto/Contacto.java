package com.autoelite.AutoElite.contacto;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String asuntoEmail;
    private String mensaje;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
}

package com.autoelite.AutoElite.Calificacion;

import com.autoelite.AutoElite.Usuarios.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receiver", referencedColumnName = "id", nullable = false)
    private Usuario receiver;

    @ManyToOne
    @JoinColumn(name = "sender", referencedColumnName = "id", nullable = false)
    private Usuario sender;

    private Date fecha;
    private String comentarios;
    private int numEstrellas;
}

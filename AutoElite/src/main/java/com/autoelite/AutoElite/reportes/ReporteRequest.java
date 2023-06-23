package com.autoelite.AutoElite.reportes;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ReporteRequest {
    private String comentarios;
    private String publicacionId;
}

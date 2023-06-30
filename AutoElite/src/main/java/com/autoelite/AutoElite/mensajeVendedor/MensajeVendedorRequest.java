package com.autoelite.AutoElite.mensajeVendedor;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MensajeVendedorRequest {

    private String telefono;
    private String email;
    private String mensaje;
    private String sender;
    private String receiver;


}

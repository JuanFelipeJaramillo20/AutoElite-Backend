package com.autoelite.AutoElite.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.autoelite.AutoElite.Usuarios.RolUsuario;
import com.autoelite.AutoElite.Usuarios.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;

@Service
public class TokenServices {

    public String generarToken(Usuario usuario) {

        try {
            Algorithm algorithm = Algorithm.HMAC256("54321");//HMAC256 recibe un string que es un 'secret' para validar la firma
            return JWT.create()
                    .withIssuer("autoelite com")
                    .withClaim("id: ", usuario.getId())
                    .withClaim( "email: ",usuario.getEmail())
                    .withClaim("nombre: ", usuario.getNombres())
                    .withClaim("telefono: ", usuario.getTelefono())
                    .withClaim("rol: ", (String.valueOf(usuario.getRolUsuario())))
                    //.withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException();
        }
    }

    //validar que el usuario a iniciado sesion
    public String getSubject(String token) {
        if (token == null){
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("54321");
            verifier = JWT.require(algorithm)
                    .withIssuer("autoelite com")
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }
        if (verifier.getSubject() == null){
            throw new RuntimeException("Verifier invalido");
        }
        return verifier.getSubject();
    }

    /*
    //darte una expiracion al token
    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }*/
}

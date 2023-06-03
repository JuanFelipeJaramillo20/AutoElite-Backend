/*package com.autoelite.AutoElite.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

@Service
public class TokenServices {

    public String generarToken() {

        try {
            Algorithm algorithm = Algorithm.HMAC256("1234567");//HMAC256 recibe un string que es un 'secret' para validar la firma
            return JWT.create()
                    .withIssuer("autoelite com")
                    .withSubject("david.franco")
                    //.withClaim("id: ", usuario.getId())
                    //.withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException();
        }
    }
}
*/
package com.autoelite.AutoElite.security;

import com.autoelite.AutoElite.Usuarios.UsuarioDAO;
import com.autoelite.AutoElite.errores.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {


    @Autowired
    private TokenServices tokenService;

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authHeader = request.getHeader("Authorization");
        //si el token no es nulo entra a activar el filtro
        if (authHeader != null) {
            var token = authHeader.replace("Bearer ", "");
            try {
                var email = tokenService.getSubject(token);
                if (email != null){
                    //token valido
                    var usuario = usuarioDAO.findByEmail(email);
                    var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.get().getAuthorities()); //forzar un inicio de sesion
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }catch (RuntimeException e){
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                ErrorMessage errorResponse = new ErrorMessage("No authenticated");

                // Convierte el objeto ErrorResponse en JSON
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonResponse = objectMapper.writeValueAsString(errorResponse);

                // Establece el tipo de contenido de la respuesta como application/json
                response.setContentType("application/json");

                // Escribe el JSON como cuerpo de la respuesta
                response.getWriter().write(jsonResponse);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}

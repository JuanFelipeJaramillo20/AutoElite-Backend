package com.autoelite.AutoElite.security;

import com.autoelite.AutoElite.Usuarios.UsuarioDAO;
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
            var email = tokenService.getSubject(token);
            if (email != null){
                //token valido
                var usuario = usuarioDAO.findByEmail(email);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()); //forzar un inicio de sesion
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
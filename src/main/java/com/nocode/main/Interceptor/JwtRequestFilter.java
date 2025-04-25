package com.nocode.main.Interceptor;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nocode.main.business.AuthBusiness;
import com.nocode.main.helper.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

      @Autowired
      private JwtUtil jwtUtil;

      @Autowired
      private AuthBusiness _auth;

      @Override
      protected void doFilterInternal(
                  @SuppressWarnings("null") HttpServletRequest request,
                  @SuppressWarnings("null") HttpServletResponse response,
                  @SuppressWarnings("null") FilterChain chain) throws ServletException, IOException {

            final String authorizationHeader = request.getHeader("Authorization");
            String id = null;
            String jwt = null;

            // Verificar si hay un encabezado Authorization válido
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                  jwt = authorizationHeader.substring(7);
                  id = jwtUtil.extractId(jwt);
            }

            if (id != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                  try {
                        if (_auth.findById(id) != null) {
                              // Crear detalles del usuario con el ID
                              UserDetails userDetails = new User(id, "{noop}password", Collections.emptyList());

                              // Verificar si el token JWT es válido
                              if (jwtUtil.isTokenValid(jwt, userDetails.getUsername())) {
                                    // Crear un objeto de autenticación
                                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                                userDetails, null, userDetails.getAuthorities());

                                    // Establecer autenticación en el SecurityContext
                                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                              } else {
                                    // Si el token no es válido, enviar un error 401 con mensaje personalizado
                                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                    response.setContentType("application/json");
                                    response.getWriter()
                                                .write("{\"status\": 401, \"message\": \"Token inválido o expirado\"}");
                                    return; // Detener el procesamiento de la solicitud
                              }
                        } else {
                              // Si el usuario no se encuentra, enviar un error 401
                              response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                              response.setContentType("application/json");
                              response.getWriter().write("{\"status\": 401, \"message\": \"Usuario no encontrado\"}");
                              return; // Detener el procesamiento de la solicitud
                        }
                  } catch (Exception e) {
                        // Manejar excepciones generadas durante la verificación
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.setContentType("application/json");
                        response.getWriter().write("{\"status\": 401, \"message\": \"Error al verificar el token\"}");
                        return; // Detener el procesamiento de la solicitud
                  }
            }

            // Continuar con la cadena de filtros si todo es correcto
            chain.doFilter(request, response);
      }

}

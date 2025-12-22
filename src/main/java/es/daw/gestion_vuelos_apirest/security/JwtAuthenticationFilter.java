package es.daw.gestion_vuelos_apirest.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        // Si no hay token, la solicitud sigue su curso (para endpoints públicos)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extraer el token (después de "Bearer ")
        final String token = authHeader.substring(7);

        // Extraer el username del token
        final String username = jwtService.extractUsername(token);

        // Verificar que no haya ya una autenticación previa
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Cargar el usuario de la base de datos
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Validar el token
            if (jwtService.isTokenValid(token, userDetails)) {

                // Crear el objeto de autenticación
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                // Asociar detalles de la solicitud
                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // Registrar al usuario como autenticado
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}

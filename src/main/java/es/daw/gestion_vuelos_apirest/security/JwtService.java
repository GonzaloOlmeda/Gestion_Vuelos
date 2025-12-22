package es.daw.gestion_vuelos_apirest.security;


import es.daw.gestion_vuelos_apirest.entity.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${secret-key}")
    private String secretKey;

    @Value("${token-expiration-ms}")
    private long jwtExpiration;

    @Value("${refresh-token-expiration-ms}")
    private long refreshExpiration;


    public String generateToken(final Usuario usuario) {
        return buildToken(usuario, jwtExpiration);
    }

    public String generateRefreshToken(final Usuario usuario) {
        return buildToken(usuario, refreshExpiration);
    }

    private String buildToken(final Usuario usuario, final long expiration) {
        // Lógica para construir el token JWT utilizando la biblioteca de JWT
        // Incluir el usuario, la clave secreta y el tiempo de expiración
        // Retornar el token generado como una cadena
        return Jwts.builder()
                .id(usuario.getId().toString())
                .claims(Map.of("username", usuario.getUsername())) // Informacion adicional
                .subject(usuario.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey())
                .compact();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }


}

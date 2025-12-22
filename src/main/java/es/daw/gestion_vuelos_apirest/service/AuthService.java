package es.daw.gestion_vuelos_apirest.service;

import es.daw.gestion_vuelos_apirest.dto.RegisterRequest;
import es.daw.gestion_vuelos_apirest.dto.TokenResponse;
import es.daw.gestion_vuelos_apirest.entity.Token;
import es.daw.gestion_vuelos_apirest.entity.Usuario;
import es.daw.gestion_vuelos_apirest.repository.TokenRepository;
import es.daw.gestion_vuelos_apirest.repository.UserRepository;
import es.daw.gestion_vuelos_apirest.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;



    public TokenResponse register(RegisterRequest request) {


        // Crear el usuario y guardarlo en la base de datos
        Usuario usuario = Usuario.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .rol(Usuario.Rol.USER)
                .build();

        Usuario savedUser = userRepository.save(usuario);

        // Generar el token JWT para el usuario registrado UTLIZANDO EL JWT SERVICE
        String jwtToken = jwtService.generateToken(savedUser);

        // Generar el refresh token JWT para el usuario registrado
        String refreshToken = jwtService.generateRefreshToken(savedUser);

        saveUserToken(savedUser, jwtToken);
        return new TokenResponse(jwtToken, refreshToken);
    }


    public TokenResponse registerAdmin(RegisterRequest request) {
        Usuario usuario = Usuario.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .rol(Usuario.Rol.ADMIN)
                .build();

        Usuario savedUser = userRepository.save(usuario);

        String jwtToken = jwtService.generateToken(savedUser);
        String refreshToken = jwtService.generateRefreshToken(savedUser);

        saveUserToken(savedUser, jwtToken);
        return new TokenResponse(jwtToken, refreshToken);
    }



    // Guarda el token del usuario en la base de datos

    private void saveUserToken(Usuario usuario, String jwtToken) {
        Token token = Token.builder()
                .usuario(usuario)
                .token(jwtToken)
                .tipo("BEARER")
                .expirado(false)
                .revocado(false)
                .build();
        tokenRepository.save(token);
    }
}

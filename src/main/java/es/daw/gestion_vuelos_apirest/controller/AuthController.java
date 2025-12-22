package es.daw.gestion_vuelos_apirest.controller;

import es.daw.gestion_vuelos_apirest.dto.RegisterRequest;
import es.daw.gestion_vuelos_apirest.dto.TokenResponse;
import es.daw.gestion_vuelos_apirest.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register (@RequestBody final RegisterRequest registerRequest) {

        final TokenResponse token = authService.register(registerRequest);
        return ResponseEntity.ok(token);

    }


    @PostMapping("/register/admin")
    public ResponseEntity<TokenResponse> registerAdmin(@RequestBody final RegisterRequest registerRequest) {
        final TokenResponse token = authService.registerAdmin(registerRequest);
        return ResponseEntity.ok(token);
    }

}

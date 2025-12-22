package es.daw.gestion_vuelos_apirest.dto;

public record RegisterRequest(

        String email,
        String password,
        String username

) {
}

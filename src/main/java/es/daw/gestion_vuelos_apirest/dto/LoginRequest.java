package es.daw.gestion_vuelos_apirest.dto;



public record LoginRequest(
        String email,
        String password
) {
}

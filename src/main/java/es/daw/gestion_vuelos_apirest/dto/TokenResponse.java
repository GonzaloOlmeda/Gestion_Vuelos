package es.daw.gestion_vuelos_apirest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenResponse(

        @JsonProperty("access_token")
        String accessToken,
        @JsonProperty("refresh_token")
        String refreshToken
) {
}

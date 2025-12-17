package es.daw.gestion_vuelos_apirest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VueloResponseDTO {

    private Long id;
    private String origen;
    private String destino;
    private Double precio;
    private Integer numeroEscalas;
    private String compania;

}

package es.daw.gestion_vuelos_apirest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class VueloRequestDTO {

    @NotBlank(message = "El origen no puede estar vacío")
    private String origen;

    @NotBlank(message = "El detino no puede estar vacío")
    private String destino;

    @NotBlank(message = "El precio no puede estar vacío")
    @Positive(message = "El precio debe ser mayor de 0")
    private Double precio;

    private Integer numero_escalas;

    @NotBlank(message = "El vuelo debe tener una compañía asignada")
    private String compania;
}

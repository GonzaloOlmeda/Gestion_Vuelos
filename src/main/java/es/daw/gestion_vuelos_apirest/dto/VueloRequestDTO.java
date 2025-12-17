package es.daw.gestion_vuelos_apirest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VueloRequestDTO {


    @NotBlank(message = "El origen es obligatorio")
    private String origen;

    @NotBlank(message = "El destino es obligatorio")
    private String destino;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor que 0")
    private Double precio;

    @NotNull(message = "El número de escalas es obligatorio")
    @Min(value = 0, message = "El número de escalas no puede ser negativo")
    private Integer numeroEscalas; // Cambiar nombre

    @NotBlank(message = "La compañía es obligatoria")
    private String compania;
}

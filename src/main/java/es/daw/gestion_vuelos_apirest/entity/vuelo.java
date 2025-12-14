// java
// file: `src/main/java/es/daw/gestion_vuelos_apirest/entity/vuelo.java`
package es.daw.gestion_vuelos_apirest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "vuelos")
public class vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String origen;

    @Column(nullable = false)
    private String destino;

    @Column(nullable = false)
    private Double precio;

    // Nombre de propiedad Java en camelCase; la columna DB sigue siendo numero_escalas
    @Column(name = "numero_escalas", nullable = false)
    private int numeroEscalas;

    @Column(nullable = false)
    private String compania;
}

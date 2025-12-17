package es.daw.gestion_vuelos_apirest.repository;


import es.daw.gestion_vuelos_apirest.dto.VueloResponseDTO;
import es.daw.gestion_vuelos_apirest.entity.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface VueloRepository extends JpaRepository<Vuelo, Long> {

    @Query("""
    SELECT new es.daw.gestion_vuelos_apirest.dto.VueloResponseDTO(
            v.id,
            v.origen,
            v.destino,
            v.precio,
            v.numeroEscalas,
            v.compania
            )
    FROM Vuelo v
    WHERE (:origen IS NULL OR v.origen = :origen)
    AND (:destino IS NULL OR v.destino = :destino)
    AND (:numeroEscalas IS NULL OR v.numeroEscalas = :numeroEscalas)
    """)


    Optional<List<VueloResponseDTO>> filterVuelos(String origen, String destino, Integer numeroEscalas);


}

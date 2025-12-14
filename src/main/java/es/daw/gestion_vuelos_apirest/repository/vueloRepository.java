package es.daw.gestion_vuelos_apirest.repository;


import es.daw.gestion_vuelos_apirest.entity.vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface vueloRepository extends JpaRepository<vuelo, Long> {

    List<vuelo> findByOrigen(String origen);
    List<vuelo> findByDestino(String destino);
    List<vuelo> findByNumeroEscalas(int numeroEscalas);


    @Query("SELECT v FROM vuelo v " +
            "WHERE (:origen IS NULL OR v.origen = :origen) " +
            "AND (:destino IS NULL OR v.destino = :destino) " +
            "AND (:numeroEscalas IS NULL OR v.numeroEscalas = :numeroEscalas)")
    List<vuelo> findByFilters(@Param("origen") String origen,
                              @Param("destino") String destino,
                              @Param("numeroEscalas") Integer numeroEscalas);
}

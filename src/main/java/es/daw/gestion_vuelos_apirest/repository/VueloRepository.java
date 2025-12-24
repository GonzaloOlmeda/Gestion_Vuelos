package es.daw.gestion_vuelos_apirest.repository;



import es.daw.gestion_vuelos_apirest.entity.Vuelo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface VueloRepository extends JpaRepository<Vuelo, Long> {


    @Query("""
        SELECT v FROM Vuelo v
        WHERE (:origen IS NULL OR LOWER(v.origen) = LOWER(:origen))
        AND (:destino IS NULL OR LOWER(v.destino) = LOWER(:destino))
        AND (:escalas IS NULL OR v.numeroEscalas = :escalas)
        """)
    Page<Vuelo> filterVuelos(
            @Param("origen") String origen,
            @Param("destino") String destino,
            @Param("escalas") Integer escalas,
            Pageable pageable
    );


    boolean existsByOrigenAndDestinoAndCompania(String origen, String destino, String compania);




}

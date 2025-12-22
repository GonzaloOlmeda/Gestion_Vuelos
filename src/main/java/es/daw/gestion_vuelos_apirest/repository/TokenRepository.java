package es.daw.gestion_vuelos_apirest.repository;

import es.daw.gestion_vuelos_apirest.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("SELECT t FROM Token t " +
            "WHERE t.usuario.id = :usuarioId " +
            "AND t.expirado = false " +
            "AND t.revocado = false")
    List<Token> findAllValidTokensByUsuario(@Param("usuarioId") Long usuarioId);

}

package es.daw.gestion_vuelos_apirest.repository;

import es.daw.gestion_vuelos_apirest.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
}

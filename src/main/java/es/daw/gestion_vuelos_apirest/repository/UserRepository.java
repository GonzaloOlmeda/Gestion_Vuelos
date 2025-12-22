package es.daw.gestion_vuelos_apirest.repository;

import es.daw.gestion_vuelos_apirest.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Long> {
}

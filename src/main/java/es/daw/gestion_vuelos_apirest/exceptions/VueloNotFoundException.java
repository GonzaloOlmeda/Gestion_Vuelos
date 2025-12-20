package es.daw.gestion_vuelos_apirest.exceptions;

public class VueloNotFoundException extends RuntimeException{
    public VueloNotFoundException(Long id) {
        super("Vuelo con ID " + id + " no encontrado.");
    }

    public VueloNotFoundException(String destino) {
        super("No se encontraron vuelos con destino: " + destino);
    }
}

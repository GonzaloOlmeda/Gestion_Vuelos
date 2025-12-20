package es.daw.gestion_vuelos_apirest.exceptions;

public class VueloAlreadyExistsException extends RuntimeException
{

    public VueloAlreadyExistsException(String origen, String destino, String compania) {
        super("Ya existe un vuelo de " + compania + " con ruta " + origen + " → " + destino);
    }
}

package es.daw.gestion_vuelos_apirest.service;

import es.daw.gestion_vuelos_apirest.dto.VueloRequestDTO;
import es.daw.gestion_vuelos_apirest.dto.VueloResponseDTO;
import es.daw.gestion_vuelos_apirest.entity.Vuelo;
import es.daw.gestion_vuelos_apirest.mapper.VueloMapper;
import es.daw.gestion_vuelos_apirest.repository.VueloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VueloService {

    private final VueloRepository vueloRepository;
    private final VueloMapper vueloMapper;


    public List<VueloResponseDTO> filterVuelos(String origen, String destino, Integer escalas) {


        return vueloRepository.filterVuelos(origen, destino, escalas)
                .orElseGet(List::of);

    }

    public void createVuelos(VueloRequestDTO vueloRequestDTO) {

        if(vueloRequestDTO== null) {
            throw new IllegalArgumentException("El vuelo no puede ser nulo");
        }

        Vuelo vuelo = new Vuelo();
        vuelo.setOrigen(vueloRequestDTO.getOrigen());
        vuelo.setDestino(vueloRequestDTO.getDestino());
        vuelo.setPrecio(vueloRequestDTO.getPrecio());
        vuelo.setNumeroEscalas(vueloRequestDTO.getNumero_escalas());
        vuelo.setCompania(vueloRequestDTO.getCompania());

        vueloRepository.save(vuelo);


    }
}
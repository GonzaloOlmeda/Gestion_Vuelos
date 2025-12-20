package es.daw.gestion_vuelos_apirest.service;

import es.daw.gestion_vuelos_apirest.dto.VueloRequestDTO;
import es.daw.gestion_vuelos_apirest.dto.VueloResponseDTO;
import es.daw.gestion_vuelos_apirest.entity.Vuelo;
import es.daw.gestion_vuelos_apirest.mapper.VueloMapper;
import es.daw.gestion_vuelos_apirest.repository.VueloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VueloService {

    private final VueloRepository vueloRepository;
    private final VueloMapper vueloMapper;


    @Transactional(readOnly = true)
    public List<VueloResponseDTO> filterVuelos(String origen, String destino, Integer escalas) {
        List<Vuelo> vuelos = vueloRepository.filterVuelos(origen, destino, escalas);
        return vueloMapper.toResponseDTOList(vuelos);
    }

    @Transactional
    public VueloResponseDTO createVuelo(VueloRequestDTO vueloRequestDTO) {

        Vuelo vuelo = vueloMapper.toEntity(vueloRequestDTO);
        Vuelo vueloGuardado = vueloRepository.save(vuelo);

        return vueloMapper.toResponseDTO(vueloGuardado);
    }

    @Transactional
    public void deleteVuelo(Long id) {

        Vuelo vuelo = vueloRepository.findById(id)
//                .orElseThrow(() -> new VueloNotFoundException("Vuelo no encontrado con ID: " + id)); // pendiente excepci��n
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado con ID: " + id)); // pendiente hacer excepci��n propia

        vueloRepository.delete(vuelo);

    }

    @Transactional
    public void deleteVueloByDestino(String destino) {
        List<Vuelo> vuelos = vueloRepository.findAll().stream()
                .filter(vuelo -> vuelo.getDestino().equalsIgnoreCase(destino))
                .toList();

        vueloRepository.deleteAll(vuelos);
    }

    @Transactional
    public VueloResponseDTO updateVuelo(Long id, VueloRequestDTO vueloRequestDTO){

        Vuelo vuelo = vueloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado con ID: " + id)); // pendiente hacer excepci��n propia


        vuelo.setOrigen(vueloRequestDTO.getOrigen());
        vuelo.setDestino(vueloRequestDTO.getDestino());
        vuelo.setPrecio(vueloRequestDTO.getPrecio());
        vuelo.setNumeroEscalas(vueloRequestDTO.getNumeroEscalas());
        vuelo.setCompania(vueloRequestDTO.getCompania());

        Vuelo vueloActualizado = vueloRepository.save(vuelo);

        return vueloMapper.toResponseDTO(vueloActualizado);
    }

}



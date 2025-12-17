package es.daw.gestion_vuelos_apirest.mapper;


import es.daw.gestion_vuelos_apirest.dto.VueloRequestDTO;
import es.daw.gestion_vuelos_apirest.dto.VueloResponseDTO;
import es.daw.gestion_vuelos_apirest.entity.Vuelo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VueloMapper {


    public VueloResponseDTO toResponseDTO(Vuelo vuelo) {
        return new VueloResponseDTO(
                vuelo.getId(),
                vuelo.getOrigen(),
                vuelo.getDestino(),
                vuelo.getPrecio(),
                vuelo.getNumeroEscalas(),
                vuelo.getCompania()
        );
    }

    public Vuelo toEntity(VueloRequestDTO dto) {
        Vuelo vuelo = new Vuelo();
        vuelo.setOrigen(dto.getOrigen());
        vuelo.setDestino(dto.getDestino());
        vuelo.setPrecio(dto.getPrecio());
        vuelo.setNumeroEscalas(dto.getNumeroEscalas());
        vuelo.setCompania(dto.getCompania());
        return vuelo;
    }

    public List<VueloResponseDTO> toResponseDTOList(List<Vuelo> vuelos) {
        return vuelos.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }


}

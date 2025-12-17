package es.daw.gestion_vuelos_apirest.mapper;


import es.daw.gestion_vuelos_apirest.dto.VueloRequestDTO;
import es.daw.gestion_vuelos_apirest.dto.VueloResponseDTO;
import es.daw.gestion_vuelos_apirest.entity.Vuelo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VueloMapper {

    public VueloResponseDTO toResponseDTO(Vuelo vuelo){

        if(vuelo == null) return null;

        VueloResponseDTO dto = new VueloResponseDTO();
        dto.setId(vuelo.getId());
        dto.setOrigen(vuelo.getOrigen());
        dto.setDestino(vuelo.getDestino());
        dto.setPrecio(vuelo.getPrecio());
        dto.setNumero_escalas(vuelo.getNumeroEscalas());
        dto.setCompania(vuelo.getCompania());

        return toResponseDTO(vuelo);

    }

    public VueloRequestDTO toRequestDTO(Vuelo vuelo){

        if(vuelo == null) return null;

        VueloResponseDTO dto = new VueloResponseDTO();
        dto.setOrigen(vuelo.getOrigen());
        dto.setDestino(vuelo.getDestino());
        dto.setPrecio(vuelo.getPrecio());
        dto.setNumero_escalas(vuelo.getNumeroEscalas());
        dto.setCompania(vuelo.getCompania());

        return toRequestDTO(vuelo);

    }




    public List<VueloResponseDTO> toDTOList(List<Vuelo> getVuelos) {

        return getVuelos.stream().map(this::toResponseDTO).toList();
    }
}

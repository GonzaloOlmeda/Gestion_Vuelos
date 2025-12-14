package es.daw.gestion_vuelos_apirest.mapper;


import es.daw.gestion_vuelos_apirest.dto.vueloDTO;
import es.daw.gestion_vuelos_apirest.entity.vuelo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class vueloMapper {

    public vueloDTO toDTO(vuelo vuelo){

        if(vuelo == null) return null;

        vueloDTO dto = new vueloDTO();
        dto.setId(vuelo.getId().toString());
        dto.setOrigen(vuelo.getOrigen());
        dto.setDestino(vuelo.getDestino());
        dto.setPrecio(vuelo.getPrecio());
        dto.setNumero_escalas(vuelo.getNumeroEscalas());
        dto.setCompania(vuelo.getCompania());

        return dto;

    }


    public List<vueloDTO> toDTOList(List<vuelo> getVuelos) {

        return getVuelos.stream().map(this::toDTO).toList();
    }
}

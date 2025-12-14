package es.daw.gestion_vuelos_apirest.service;

import es.daw.gestion_vuelos_apirest.dto.vueloDTO;
import es.daw.gestion_vuelos_apirest.entity.vuelo;
import es.daw.gestion_vuelos_apirest.mapper.vueloMapper;
import es.daw.gestion_vuelos_apirest.repository.vueloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class vueloService {

    private final vueloRepository vueloRepository;
    private final vueloMapper vueloMapper;


    public List<vueloDTO> filterVuelos(String origen, String destino, Integer escalas){
       List<vuelo> vuelos;

      if(origen == null && destino == null && escalas == null){
          vuelos = vueloRepository.findAll();
      } else {
          vuelos = vueloRepository.findByFilters(origen, destino, escalas);
      }

       return vueloMapper.toDTOList(vuelos);


    }


}

package es.daw.gestion_vuelos_apirest.controller;

import es.daw.gestion_vuelos_apirest.dto.vueloDTO;
import es.daw.gestion_vuelos_apirest.service.vueloService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vuelos")
@RequiredArgsConstructor
public class vueloController {

    private final vueloService vueloService;

    @GetMapping()
    public ResponseEntity<List<vueloDTO>> filterVuelos(@RequestParam(required = false) String origen,
                                                       @RequestParam(required = false) String destino,
                                                       @RequestParam(required = false) Integer escalas){
        return ResponseEntity.ok(vueloService.filterVuelos(origen, destino, escalas));
    }



}

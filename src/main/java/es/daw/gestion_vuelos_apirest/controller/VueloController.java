package es.daw.gestion_vuelos_apirest.controller;

import es.daw.gestion_vuelos_apirest.dto.VueloRequestDTO;
import es.daw.gestion_vuelos_apirest.dto.VueloResponseDTO;
import es.daw.gestion_vuelos_apirest.service.VueloService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vuelos")
@RequiredArgsConstructor
public class VueloController {

    private final VueloService vueloService;

    @GetMapping()
    public ResponseEntity<List<VueloResponseDTO>> filterVuelos(@RequestParam(required = false) String origen,
                                                               @RequestParam(required = false) String destino,
                                                               @RequestParam(required = false) Integer escalas){
        return ResponseEntity.ok(vueloService.filterVuelos(origen, destino, escalas));
    }

    @PostMapping()
    public ResponseEntity<Void> createVuelos(@RequestBody VueloRequestDTO vueloRequestDTO){
        vueloService.createVuelos(vueloRequestDTO);
        return ResponseEntity.status(201).build();
    }

}

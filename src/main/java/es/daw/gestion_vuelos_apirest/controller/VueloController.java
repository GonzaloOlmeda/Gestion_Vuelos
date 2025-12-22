package es.daw.gestion_vuelos_apirest.controller;

import es.daw.gestion_vuelos_apirest.dto.VueloRequestDTO;
import es.daw.gestion_vuelos_apirest.dto.VueloRequestParcialDTO;
import es.daw.gestion_vuelos_apirest.dto.VueloResponseDTO;
import es.daw.gestion_vuelos_apirest.service.VueloService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        List<VueloResponseDTO> vuelos = vueloService.filterVuelos(origen, destino, escalas);
        return ResponseEntity.ok(vuelos);
    }

    @PostMapping("/create")
    public ResponseEntity<VueloResponseDTO> createVuelo(@Valid @RequestBody VueloRequestDTO vueloRequestDTO){
       VueloResponseDTO vueloCreado =  vueloService.createVuelo(vueloRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(vueloCreado);
    }


    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<Void> deleteVuelo(@PathVariable Long id){
        vueloService.deleteVuelo(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/destino/{destino}")
    public ResponseEntity<Void> deleteVueloByDestino(@PathVariable String destino){
        vueloService.deleteVueloByDestino(destino);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<VueloResponseDTO> updateVuelo(@Valid @PathVariable Long id, @RequestBody VueloRequestDTO vueloRequestDTO){
        VueloResponseDTO vueloActualizado = vueloService.updateVuelo(id, vueloRequestDTO);
        return ResponseEntity.ok(vueloActualizado);

    }

    @PostMapping("/partial-update/{id}" )
    public ResponseEntity<VueloResponseDTO> updateVueloParcial(@Valid @PathVariable Long id, @RequestBody VueloRequestParcialDTO vueloRequestParcialDTO){
        VueloResponseDTO vueloActualizado = vueloService.updateVueloPartial(id, vueloRequestParcialDTO);
        return  ResponseEntity.ok(vueloActualizado);
    }



}

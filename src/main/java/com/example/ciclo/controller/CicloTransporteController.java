package com.example.ciclo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ciclo.dto.ActualizarEstadoDTO;
import com.example.ciclo.dto.IniciarCicloDTO;
import com.example.ciclo.mapper.CicloMapper;
import com.example.ciclo.model.CicloTransporte;
import com.example.ciclo.service.CicloTransporteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/ciclos-transporte")
public class CicloTransporteController {

    private final CicloTransporteService cicloService;
    private final CicloMapper cicloMapper;

    public CicloTransporteController(CicloTransporteService cicloService, CicloMapper cicloMapper) {
        this.cicloService = cicloService;
        this.cicloMapper = cicloMapper;
    }

    //listar todos los ciclos
    @GetMapping
    public ResponseEntity<List<CicloTransporte>> listarTodos() {
        return ResponseEntity.ok(cicloService.obtenerTodosLosCiclos());
    }

    //registrar inicio de un ciclo
    @PostMapping("/iniciar")
    public ResponseEntity<CicloTransporte> iniciarViaje(@Valid @RequestBody IniciarCicloDTO dto) {
        CicloTransporte nuevoCiclo = cicloMapper.toEntity(dto);

        CicloTransporte guardado = cicloService.guardarCicloEntidad(nuevoCiclo);
        
        return new ResponseEntity<>(guardado, HttpStatus.CREATED);
    }

    //actualizar estado del ciclo o viaje
    @PutMapping("/estado")
    public ResponseEntity<CicloTransporte> actualizarEstado(@Valid @RequestBody ActualizarEstadoDTO dto) {
        CicloTransporte actualizado = cicloService.actualizarEstado(
            dto.getId(),
            dto.getNuevoEstado(),
            dto.getDestino(),
            dto.getToneladas()
        );
        return ResponseEntity.ok(actualizado);
    }

    //listar los ciclos de un camión
    @GetMapping("/camion/{camionId}")
    public ResponseEntity<List<CicloTransporte>> listarPorCamion(@PathVariable int camionId) {
        return ResponseEntity.ok(cicloService.obtenerCiclosPorCamion(camionId));
    }

}

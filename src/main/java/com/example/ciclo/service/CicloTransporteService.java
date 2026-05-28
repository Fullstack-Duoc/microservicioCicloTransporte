package com.example.ciclo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ciclo.exception.CicloValidationException;
import com.example.ciclo.exception.ResourceNotFoundException;
import com.example.ciclo.model.CicloTransporte;
import com.example.ciclo.model.Destino;
import com.example.ciclo.model.EstadoCiclo;
import com.example.ciclo.repository.CicloTransporteRepository;

@Service
public class CicloTransporteService {

    private final CicloTransporteRepository cicloRepository;
    private final ExternalValidationService validationService;

    public CicloTransporteService(CicloTransporteRepository cicloRepository, ExternalValidationService validationService) {
        this.cicloRepository = cicloRepository;
        this.validationService = validationService;
    }

    public CicloTransporte guardarCicloEntidad(CicloTransporte c) {
        return cicloRepository.save(c);
    }

    public List<CicloTransporte> obtenerTodosLosCiclos() {
        return cicloRepository.findAll();
    }

    public CicloTransporte iniciarCiclo(int camionId, int palaId, int paleroId) {

        boolean camionValido = validationService.verificarCamionActivo((long) camionId);
        if (!camionValido) {
            throw new CicloValidationException("No se puede iniciar el ciclo porque el ID de camión "
            + camionId + " no está autorizado o se encuentra en mantención");
        }

        boolean palaValida = validationService.verificarPalaActiva((long) palaId);
        if (!palaValida) {
            throw new CicloValidationException("No se puede iniciar el ciclo porque el ID de pala mecánica " 
            + palaId + " no existe o no está asignada a este frente.");
        }

        boolean paleroValido = validationService.verificarPaleroAutorizado((long) paleroId);
        if (!paleroValido) {
            throw new CicloValidationException("No se puede iniciar el ciclo porque el ID de operador palero " 
            + paleroId + " no cuenta con certificación vigente.");
        }

        CicloTransporte nuevoCiclo = new CicloTransporte();
        nuevoCiclo.setCamionId(camionId);
        nuevoCiclo.setPalaId(palaId);
        nuevoCiclo.setPaleroId(paleroId);
        nuevoCiclo.setEstadoCiclo(EstadoCiclo.EN_TRANSITO);
        nuevoCiclo.setFechaHoraInicio(LocalDateTime.now());

        return cicloRepository.save(nuevoCiclo);
    }

    public CicloTransporte actualizarEstado(int cicloId, EstadoCiclo nuevoEstado, Destino destino, Double toneladas) {

        //primero se busca el ciclo. si no existe lanzamos error.
        CicloTransporte ciclo = cicloRepository.findById(cicloId)
        .orElseThrow(() -> new ResourceNotFoundException("El ciclo con ID " + cicloId + " no existe."));

        ciclo.setEstadoCiclo(nuevoEstado);

        //si está CARGANDO se define qué lleva y a dónde va
        if (nuevoEstado == EstadoCiclo.CARGANDO) {
            if (destino != null) ciclo.setDestino(destino);
            if (toneladas != null) ciclo.setToneladasCargadas(toneladas);
        }

        if (nuevoEstado == EstadoCiclo.COMPLETADO) {
            ciclo.setFechaHoraFin(LocalDateTime.now());
        }

        return ciclo;
    }

    public List<CicloTransporte> obtenerCiclosPorCamion(int camionId) {
        return cicloRepository.findByCamionId(camionId);
    }

}

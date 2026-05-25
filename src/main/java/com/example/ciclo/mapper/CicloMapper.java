package com.example.ciclo.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.example.ciclo.dto.IniciarCicloDTO;
import com.example.ciclo.model.CicloTransporte;
import com.example.ciclo.model.EstadoCiclo;

@Component
public class CicloMapper {

    public CicloTransporte toEntity(IniciarCicloDTO dto) {

        if (dto==null) return null;

        CicloTransporte ciclo = new CicloTransporte();

        ciclo.setCamionId(dto.getCamionId() != null ? dto.getCamionId() : 0);
        ciclo.setPalaId(dto.getPalaId() != null ? dto.getPalaId() : 0);
        ciclo.setPaleroId(dto.getPaleroId() != null ? dto.getPaleroId() : 0);

        ciclo.setEstadoCiclo(EstadoCiclo.EN_TRANSITO);
        ciclo.setFechaHoraInicio(LocalDateTime.now());

        return ciclo;
    }

}

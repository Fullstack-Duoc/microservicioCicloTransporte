package cl.duoc.mineria.cicloTransporte.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import cl.duoc.mineria.cicloTransporte.dto.CicloTransporteResponseDTO;
import cl.duoc.mineria.cicloTransporte.dto.IniciarCicloDTO;
import cl.duoc.mineria.cicloTransporte.model.CicloTransporte;
import cl.duoc.mineria.cicloTransporte.model.EstadoCiclo;

@Component
public class CicloMapper {

    public CicloTransporte toEntity(IniciarCicloDTO dto) {

        if (dto==null) return null;

        CicloTransporte ciclo = new CicloTransporte();

        ciclo.setCamionId(dto.getCamionId() != null ? dto.getCamionId() : 0L);
        ciclo.setPalaId(dto.getPalaId() != null ? dto.getPalaId() : 0L);
        ciclo.setPaleroId(dto.getPaleroId() != null ? dto.getPaleroId() : 0L);

        ciclo.setEstadoCiclo(EstadoCiclo.EN_TRANSITO);
        ciclo.setFechaHoraInicio(LocalDateTime.now());

        return ciclo;
    }

    public CicloTransporteResponseDTO toResponseDTO(CicloTransporte entity) {
        if (entity == null) return null;

        return CicloTransporteResponseDTO.builder()
                .id(entity.getId())
                .camionId(entity.getCamionId())
                .palaId(entity.getPalaId())
                .paleroId(entity.getPaleroId())
                .materialId(entity.getMaterialId())
                .destino(entity.getDestino())
                .toneladasCargadas(entity.getToneladasCargadas())
                .estadoCiclo(entity.getEstadoCiclo())
                .fechaHoraInicio(entity.getFechaHoraInicio())
                .fechaHoraFin(entity.getFechaHoraFin())
                .build();
    }

}

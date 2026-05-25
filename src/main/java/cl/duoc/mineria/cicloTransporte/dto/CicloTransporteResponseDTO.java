package cl.duoc.mineria.cicloTransporte.dto;

import cl.duoc.mineria.cicloTransporte.model.Destino;
import cl.duoc.mineria.cicloTransporte.model.EstadoCiclo;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CicloTransporteResponseDTO {

    private Long id;
    private Long camionId;
    private Long palaId;
    private Long paleroId;
    private Long materialId;
    private Destino destino;
    private Double toneladasCargadas;
    private EstadoCiclo estadoCiclo;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    
}
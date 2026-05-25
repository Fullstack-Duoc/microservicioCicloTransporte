package cl.duoc.mineria.cicloTransporte.dto;

import cl.duoc.mineria.cicloTransporte.model.Destino;
import cl.duoc.mineria.cicloTransporte.model.EstadoCiclo;
import lombok.Data;

@Data
public class ActualizarEstadoDTO {
    private EstadoCiclo nuevoEstado;
    private Destino destino;
    private Double toneladas;
}
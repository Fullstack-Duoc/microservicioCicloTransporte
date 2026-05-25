package com.example.ciclo.dto;

import com.example.ciclo.model.Destino;
import com.example.ciclo.model.EstadoCiclo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActualizarEstadoDTO {

    @NotNull(message = "El ID del ciclo es obligatorio")
    @Min(value = 1, message = "El ID del ciclo debe ser un número entero mayor que 0")
    private Integer id;

    @NotNull(message = "El nuevo estado del ciclo es obligatorio")
    private EstadoCiclo nuevoEstado;
    
    private Destino destino;
    private Double toneladas;
}

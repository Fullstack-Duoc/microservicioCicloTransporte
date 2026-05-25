package com.example.ciclo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IniciarCicloDTO {

    @NotNull(message = "El ID del camión es obligatorio")
    @Min(value = 1, message = "El ID del camión debe ser mayor que 0")
    private Integer camionId;

    @NotNull(message = "El ID de la pala es obligatorio")
    @Min(value = 1, message = "El ID de la pala debe ser mayor que 0")
    private Integer palaId;

    @NotNull(message = "El ID del palero es obligatorio")
    @Min(value = 1, message = "El ID del palero debe ser mayor que 0")
    private Integer paleroId;
}

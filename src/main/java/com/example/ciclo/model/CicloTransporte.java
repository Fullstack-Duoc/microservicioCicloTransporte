package com.example.ciclo.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CicloTransporte {

    private int id;
    private int camionId;
    private int palaId;
    private int paleroId;
    private int materialId;
    private Destino destino;
    private double toneladasCargadas;
    private EstadoCiclo estadoCiclo;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;

}

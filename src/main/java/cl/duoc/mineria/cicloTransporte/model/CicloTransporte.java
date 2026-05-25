package cl.duoc.mineria.cicloTransporte.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ciclos_transporte")
public class CicloTransporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "camion_id", nullable = false)
    private Long camionId;

    @Column(name = "pala_id", nullable = false)
    private Long palaId;

    @Column(name = "palero_id", nullable = false)
    private Long paleroId;

    @Column(name = "material_id")
    private Long materialId;

    @Enumerated(EnumType.STRING)
    @Column(name = "destino")
    private Destino destino;

    @Column(name = "toneladas_cargadas")
    private Double toneladasCargadas;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_ciclo", nullable = false)
    private EstadoCiclo estadoCiclo;

    @Column(name = "fecha_hora_inicio")
    private LocalDateTime fechaHoraInicio;

    @Column(name = "fecha_hora_fin")
    private LocalDateTime fechaHoraFin;

}

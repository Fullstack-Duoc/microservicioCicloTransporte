package cl.duoc.mineria.cicloTransporte.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired; // Importamos el Autowired
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import cl.duoc.mineria.cicloTransporte.dto.IniciarCicloDTO;
import cl.duoc.mineria.cicloTransporte.exception.ResourceNotFoundException;
import cl.duoc.mineria.cicloTransporte.model.CicloTransporte;
import cl.duoc.mineria.cicloTransporte.model.Destino;
import cl.duoc.mineria.cicloTransporte.model.EstadoCiclo;
import cl.duoc.mineria.cicloTransporte.repository.CicloTransporteRepository;

@Service
public class CicloTransporteService {

    @Autowired // Inyección limpia por campo
    private CicloTransporteRepository cicloRepository;

    @Autowired
    private WebClient webClient;

    // 1. LISTAR TODOS
    public List<CicloTransporte> obtenerTodosLosCiclos() {
        return cicloRepository.findAll();
    }

    // 1.1 OBTENER POR ID
    public CicloTransporte obtenerPorId(Long id) {
        return cicloRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Ciclo no encontrado con ID: " + id));
    }

    // 2. INICIAR CICLO
    public CicloTransporte iniciarCiclo(IniciarCicloDTO dto) {
        CicloTransporte nuevoCiclo = new CicloTransporte();
        nuevoCiclo.setCamionId(dto.getCamionId());
        nuevoCiclo.setPalaId(dto.getPalaId());
        nuevoCiclo.setPaleroId(dto.getPaleroId());
        
        nuevoCiclo.setEstadoCiclo(EstadoCiclo.EN_TRANSITO);
        nuevoCiclo.setFechaHoraInicio(LocalDateTime.now());

        return cicloRepository.save(nuevoCiclo);
    }

    // 3. ACTUALIZAR ESTADO
    public CicloTransporte actualizarEstado(Long cicloId, EstadoCiclo nuevoEstado, Destino destino, Double toneladas) {
        CicloTransporte ciclo = cicloRepository.findById(cicloId)
            .orElseThrow(() -> new ResourceNotFoundException("El ciclo con ID " + cicloId + " no existe."));

        ciclo.setEstadoCiclo(nuevoEstado);

        if (nuevoEstado == EstadoCiclo.CARGANDO) {
            if (destino != null) ciclo.setDestino(destino);
            if (toneladas != null) ciclo.setToneladasCargadas(toneladas);
        }

        if (nuevoEstado == EstadoCiclo.COMPLETADO) {
            ciclo.setFechaHoraFin(LocalDateTime.now());
        }

        return cicloRepository.save(ciclo);
    }

    // 4. LISTAR POR CAMIÓN
    public List<CicloTransporte> obtenerCiclosPorCamion(Long camionId) {
        return cicloRepository.findByCamionId(camionId);
    }

    // 5. LISTAR ACTIVOS (No completados)
    public List<CicloTransporte> obtenerCiclosActivos() {
        return cicloRepository.findByEstadoCicloNot(EstadoCiclo.COMPLETADO);
    }

    // 6. ELIMINAR CICLO
    public void eliminarCiclo(Long id) {
        if (!cicloRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. Ciclo no encontrado con ID: " + id);
        }
        cicloRepository.deleteById(id);
    }
}
package cl.duoc.mineria.cicloTransporte.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.duoc.mineria.cicloTransporte.dto.ActualizarEstadoDTO;
import cl.duoc.mineria.cicloTransporte.dto.IniciarCicloDTO;
import cl.duoc.mineria.cicloTransporte.model.CicloTransporte;
import cl.duoc.mineria.cicloTransporte.service.CicloTransporteService;

@RestController
@RequestMapping("/api/ciclos")
public class CicloTransporteController {

    @Autowired
    private CicloTransporteService cicloService;

    @GetMapping
    public List<CicloTransporte> listarTodos() {
        return cicloService.obtenerTodosLosCiclos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CicloTransporte> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cicloService.obtenerPorId(id));
    }

    @GetMapping("/camion/{camionId}")
    public List<CicloTransporte> listarPorCamion(@PathVariable Long camionId) {
        return cicloService.obtenerCiclosPorCamion(camionId);
    }

    @GetMapping("/activos")
    public List<CicloTransporte> listarActivos() {
        return cicloService.obtenerCiclosActivos();
    }

    @PostMapping
    public ResponseEntity<CicloTransporte> iniciar(@RequestBody IniciarCicloDTO dto) {
        return ResponseEntity.ok(cicloService.iniciarCiclo(dto));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<CicloTransporte> actualizarEstado(
            @PathVariable Long id, 
            @RequestBody ActualizarEstadoDTO dto) {
        return ResponseEntity.ok(cicloService.actualizarEstado(
                id, dto.getNuevoEstado(), dto.getDestino(), dto.getToneladas()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cicloService.eliminarCiclo(id);
        return ResponseEntity.noContent().build();
    }
}
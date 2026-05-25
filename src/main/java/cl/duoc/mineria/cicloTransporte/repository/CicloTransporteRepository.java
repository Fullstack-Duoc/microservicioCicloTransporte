package cl.duoc.mineria.cicloTransporte.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.mineria.cicloTransporte.model.EstadoCiclo;
import cl.duoc.mineria.cicloTransporte.model.CicloTransporte;

@Repository
public interface CicloTransporteRepository extends JpaRepository<CicloTransporte, Long> {

    // Spring lee el nombre del método y genera el SQL: "SELECT * FROM ciclos_transporte WHERE camion_id = ?"
    List<CicloTransporte> findByCamionId(Long camionId);

    List<CicloTransporte> findByEstadoCicloNot(EstadoCiclo estado);
}
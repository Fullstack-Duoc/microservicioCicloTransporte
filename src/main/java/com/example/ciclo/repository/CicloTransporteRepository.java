package com.example.ciclo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.ciclo.model.CicloTransporte;

@Repository
public interface CicloTransporteRepository extends JpaRepository<CicloTransporte, Integer> {
    
    // 🌟 ¡Query Method automático! 
    // Spring lee el nombre del método y genera el SQL: "SELECT * FROM ciclos_transporte WHERE camion_id = ?"
    List<CicloTransporte> findByCamionId(int camionId);
}
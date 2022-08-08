
package com.moto.service.repositorio;

import com.moto.service.entidades.Moto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MotoRepository extends JpaRepository<Moto,Long>{
    
    List<Moto> findByUsuarioId(Long usuarioId);
}

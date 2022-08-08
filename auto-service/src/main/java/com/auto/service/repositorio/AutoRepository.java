package com.auto.service.repositorio;


import com.auto.service.entidades.Auto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoRepository extends JpaRepository<Auto, Long>{
    
    List<Auto> findByUsuarioId(Long usuarioId);
    
    
}

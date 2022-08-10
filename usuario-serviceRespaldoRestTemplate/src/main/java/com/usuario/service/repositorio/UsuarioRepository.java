package com.usuario.service.repositorio;

import com.usuario.service.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    
    
    
}

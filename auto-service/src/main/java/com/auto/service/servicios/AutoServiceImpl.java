package com.auto.service.servicios;

import com.auto.service.entidades.Auto;
import com.auto.service.repositorio.AutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoServiceImpl implements AutoService {
    
    @Autowired
    private AutoRepository autoRepository;
    
    @Override
    public List<Auto> findByUsuarioId(Long usuarioId) {
        System.out.println("Entro al Servicio");
        List<Auto> autos = autoRepository.findByUsuarioId(usuarioId);
        return autos;
    }    
    
    @Override
    public List<Auto> getAll() {
        System.out.println("Servicio");
        List<Auto> autos = autoRepository.findAll();
        
        return autos;
    }
    
    @Override
    public Auto getAutoById(Long id) {
        System.out.println("Servicio");
        return autoRepository.findById(id).orElse(null);
    }
    
    @Override
    public Auto save(Auto auto) {
        System.out.println("Servicio");
        Auto nuevoAuto = autoRepository.save(auto);
        return nuevoAuto;
    }
    
    @Override
    public Auto actualizarAuto(Long id, Auto auto) {
        Auto autoExiste = autoRepository.findById(id).orElse(null);
        if (autoExiste == null) {
            return null;
        }
        
        return autoRepository.save(auto);
    }
    
    @Override
    public void eliminarAuto(Long id) {
        try {
            autoRepository.deleteById(id);
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
    }
    
}

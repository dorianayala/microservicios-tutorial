package com.moto.service.servicio;

import com.moto.service.entidades.Moto;
import com.moto.service.repositorio.MotoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotoServiceImpl implements MotoService{

    @Autowired
    MotoRepository motoRepository;
    
    
    @Override
    public List<Moto> findByUsuarioId(Long usuarioId) {
           
        List<Moto> motos = motoRepository.findByUsuarioId(usuarioId);
        return motos;
    }

    @Override
    public List<Moto> getAll() {
          
        List<Moto> motos = motoRepository.findAll();
        
        return motos;
    }

    @Override
    public Moto getMotoById(Long id) {
           
        return motoRepository.findById(id).orElse(null);
    }

    @Override
    public Moto save(Moto moto) {
        Moto nuevoMoto = motoRepository.save(moto);
        return nuevoMoto;
    }

    @Override
    public Moto actualizarMoto(Long id, Moto moto) {
         Moto motoExiste = motoRepository.findById(id).orElse(null);
        if (motoExiste == null) {
            return null;
        }
        
        return motoRepository.save(moto);
    }

    @Override
    public void eliminarMoto(Long id) {
          try {
            motoRepository.deleteById(id);
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }
    
}

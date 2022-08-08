package com.auto.service.servicios;


import com.auto.service.entidades.Auto;
import java.util.List;

public interface AutoService {

    List<Auto> findByUsuarioId(Long usuarioId);

    public List<Auto> getAll();

    public Auto getAutoById(Long id);

    public Auto save(Auto usuario);

    public Auto actualizarAuto(Long id, Auto carro);
    
    public void eliminarAuto(Long id);

}

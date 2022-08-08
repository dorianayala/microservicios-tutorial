
package com.moto.service.servicio;

import com.moto.service.entidades.Moto;
import java.util.List;


public interface MotoService {
    List<Moto> findByUsuarioId(Long usuarioId);

    public List<Moto> getAll();

    public Moto getMotoById(Long id);

    public Moto save(Moto moto);

    public Moto actualizarMoto(Long id, Moto moto);
    
    public void eliminarMoto(Long id);

}

package com.usuario.service.servicio;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.modelos.Auto;
import com.usuario.service.modelos.Moto;
import java.util.List;

public interface UsuarioService {
    
    public Auto saveAuto(Long usuarioId, Auto auto);
            
    public Moto saveMoto(Long usuarioId, Moto moto);

    public List<Moto> getMotos(Long usuarioId);
    
    public List<Auto> getAutos(Long usuarioId);
    
    public List<Usuario> getAll();

    public Usuario getUsuarioById(Long id);

    public Usuario save(Usuario usuario);
    
     public Usuario actualizarUsuario(Long id, Usuario usuario);
     
     public void eliminarUsuario(Long id);
}

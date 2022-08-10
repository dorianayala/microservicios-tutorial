package com.usuario.service.servicio;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.feignclients.AutoFeignClient;
import com.usuario.service.feignclients.MotoFeignClient;
import com.usuario.service.modelos.Auto;
import com.usuario.service.modelos.Moto;
import com.usuario.service.repositorio.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AutoFeignClient autoFeignClient;
    
    
    
    @Autowired
    private MotoFeignClient motoFeignClient;
    
    
    @Override
    public List<Auto> getAutos(Long usuarioId) {
        List<Auto> autos = restTemplate.getForObject("http://localhost:8002/auto/usuario/" + usuarioId, List.class);
        return autos;
    }

    /**
     * *
     * Método del RestTemplate
     *
     * @param usuarioId
     * @return
     */
    @Override
    public List<Moto> getMotos(Long usuarioId) {
        List<Moto> motos = restTemplate.getForObject("http://localhost:8003/moto/usuario/" + usuarioId, List.class);
        return motos;
    }

    public List<Usuario> getAll() {

        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios;
    }

    /**
     * *********************************************
     */

    @Override
    public Auto saveAuto(Long usuarioId, Auto auto) {
        auto.setUsuarioId(usuarioId);
        Auto nuevoAuto = autoFeignClient.save(auto);
        return nuevoAuto;
    }

    public Moto saveMoto(Long usuarioId, Moto moto) {
        moto.setUsuarioId(usuarioId);
        Moto nuevoMoto = motoFeignClient.save(moto);
        return moto;
    }

    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario) {
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return nuevoUsuario;
    }

    @Override
    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        Usuario usuarioExiste = usuarioRepository.findById(id).orElse(null);
        if (usuarioExiste == null) {
            return null;
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarUsuario(Long id) {
        try {
            usuarioRepository.deleteById(id);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }


}

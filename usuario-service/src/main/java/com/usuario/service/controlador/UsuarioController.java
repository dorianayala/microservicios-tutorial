package com.usuario.service.controlador;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.modelos.Auto;
import com.usuario.service.modelos.Moto;
import com.usuario.service.servicio.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    
    @Autowired
    private UsuarioService usuarioService;
    
    /***
     * RestTemplate
     * @param usuarioId
     * @return 
     */
    
    @GetMapping("/autos/{usuarioId}")
    public ResponseEntity<List<Auto>> getAutos(@PathVariable("usuarioId") Long usuarioId){
        Usuario usuario = usuarioService.getUsuarioById(usuarioId); 
        if(usuario==null){
            return ResponseEntity.notFound().build();
        }
        List<Auto> autos = usuarioService.getAutos(usuarioId);
     
        if(autos.isEmpty()){
            return ResponseEntity.noContent().build();
        }

         return new ResponseEntity<>(autos, HttpStatus.OK);
    }
    
    @GetMapping("/motos/{usuarioId}")
    public ResponseEntity<List<Moto>> getMotos(@PathVariable("usuarioId") Long usuarioId){
        
        Usuario usuario = usuarioService.getUsuarioById(usuarioId); 
        if(usuario==null){
            return ResponseEntity.notFound().build();
        }
        List<Moto> motos = usuarioService.getMotos(usuarioId);
     
        if(motos.isEmpty()){
            return ResponseEntity.noContent().build();
        }

         return new ResponseEntity<>(motos, HttpStatus.OK);
    }
    
    /*****************************************************************************************************************************/
    /****************************************************FeignClient***********************************************************/
    /*****************************************************************************************************************************/
    
    
    @PostMapping("/auto/{usuarioId}")
    public ResponseEntity<Auto> guardarAuto(@PathVariable("usuarioId") Long id,@RequestBody Auto auto){
        Auto nuevoAuto = usuarioService.saveAuto(id, auto);
        return new ResponseEntity<>(nuevoAuto,HttpStatus.CREATED);
        
    }
    
    @GetMapping
    public ResponseEntity<List<Usuario>> findAllUsers(){
        System.out.println("Entro al metodo");
        List<Usuario> usuarios = usuarioService.getAll();
        System.out.println("Lista " + usuarios);
        if(usuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }

         return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") Long id){
        Usuario usuario = usuarioService.getUsuarioById(id);
        if(usuario == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(usuario,HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Usuario> agregarUsuario(@RequestBody Usuario usuario){
        Usuario usuarioNew = usuarioService.save(usuario);
        return new ResponseEntity<>(usuarioNew,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable("id") Long id, @RequestBody Usuario usuario){
       //Usuario usuarioActualizada = 
        Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, usuario);
        if(usuarioActualizado == null){
            return ResponseEntity.notFound().build();
        }
        
        usuarioActualizado.setNombre(usuario.getNombre());
        usuarioActualizado.setEmail(usuario.getEmail());
        usuarioActualizado = usuarioService.save(usuarioActualizado);
        return new ResponseEntity<>(usuarioActualizado,HttpStatus.OK);
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
        usuarioService.eliminarUsuario(id);
        return new ResponseEntity<>("Usuario eliminado con exito",HttpStatus.OK);


    }
    
    
    

}//Fin de clase



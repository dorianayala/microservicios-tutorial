
package com.auto.service.controladores;

import com.auto.service.entidades.Auto;
import com.auto.service.servicios.AutoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auto")
public class AutoController {
 
    @Autowired
    private AutoService autoService;
    
      @GetMapping
    public ResponseEntity<List<Auto>> findAllAutos(){
        
          List<Auto> autos = autoService.getAll();
        
        if(autos.isEmpty()){
            return ResponseEntity.noContent().build();
        }

         return new ResponseEntity<>(autos, HttpStatus.OK);
    }
    
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Auto> obtenerAuto(@PathVariable("id") Long id) {
        System.out.println("Entro al controlador");
        Auto carro = autoService.getAutoById(id);
        if(carro == null){
            return ResponseEntity.notFound().build();
        }
        System.out.println("Traigo objeto" + carro.getModelo());
        return new ResponseEntity<>(carro,HttpStatus.OK);

     
    }

    @PostMapping
    public ResponseEntity<Auto> agregarAuto(@RequestBody Auto auto) {
        System.out.println("Entro al controlador");
        Auto autoNew = autoService.save(auto);
        return new ResponseEntity<>(autoNew,HttpStatus.CREATED);

        
    }

    @PutMapping("/{id}")
    public ResponseEntity<Auto> actualizar(@PathVariable("id") Long id, @RequestBody Auto carro) {
        System.out.println("Entro al controlador");
        Auto autoActualizado = autoService.actualizarAuto(id, carro);
        if(autoActualizado == null){
            return ResponseEntity.notFound().build();
        }
        
        autoActualizado.setMarca(carro.getMarca());
        autoActualizado.setModelo(carro.getModelo());
        autoActualizado.setUsuarioId(carro.getUsuarioId());
        
        
        autoActualizado = autoService.save(autoActualizado);
        return new ResponseEntity<>(autoActualizado,HttpStatus.OK);


    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
        autoService.eliminarAuto(id);
        return new ResponseEntity<>("Auto eliminado con exito",HttpStatus.OK);


    }
    
    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Auto>> listarCarrosPorUsuario(@PathVariable("usuarioId") Long id) {
        System.out.println("Entro al controlador de Auto metodo listarCarrosPorUsuario");
        List<Auto> autos =  autoService.findByUsuarioId(id);
          if(autos == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(autos,HttpStatus.OK);
    }
    
}

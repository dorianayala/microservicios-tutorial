package com.moto.service.controlador;


import com.moto.service.entidades.Moto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.moto.service.servicio.MotoService;

@RestController
@RequestMapping("/moto")
public class MotoContoller {


   @Autowired
    private MotoService autoService;
    
    @GetMapping
    public ResponseEntity<List<Moto>> findAllMotos(){
        
          List<Moto> motos = autoService.getAll();
        
        if(motos.isEmpty()){
            return ResponseEntity.noContent().build();
        }

         return new ResponseEntity<>(motos, HttpStatus.OK);
    }
    
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Moto> obtenerMoto(@PathVariable("id") Long id) {
        System.out.println("Entro al controlador");
        Moto carro = autoService.getMotoById(id);
        if(carro == null){
            return ResponseEntity.notFound().build();
        }
        System.out.println("Traigo objeto" + carro.getModelo());
        return new ResponseEntity<>(carro,HttpStatus.OK);

     
    }

    @PostMapping
    public ResponseEntity<Moto> agregarMoto(@RequestBody Moto moto) {
        System.out.println("Entro al controlador");
        Moto autoNew = autoService.save(moto);
        return new ResponseEntity<>(autoNew,HttpStatus.CREATED);

        
    }

    @PutMapping("/{id}")
    public ResponseEntity<Moto> actualizar(@PathVariable("id") Long id, @RequestBody Moto moto) {
        System.out.println("Entro al controlador");
        Moto autoActualizado = autoService.actualizarMoto(id, moto);
        if(autoActualizado == null){
            return ResponseEntity.notFound().build();
        }
        
        autoActualizado.setMarca(moto.getMarca());
        autoActualizado.setModelo(moto.getModelo());
        autoActualizado.setUsuarioId(moto.getUsuarioId());
        
        
        autoActualizado = autoService.save(autoActualizado);
        return new ResponseEntity<>(autoActualizado,HttpStatus.OK);


    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
        autoService.eliminarMoto(id);
        return new ResponseEntity<>("Moto eliminado con exito",HttpStatus.OK);


    }
    
    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Moto>> listarCarrosPorUsuario(@PathVariable("usuarioId") Long id) {
        System.out.println("Entro al controlador");
        List<Moto> motos =  autoService.findByUsuarioId(id);
          if(motos == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(motos,HttpStatus.OK);
    }
    

    
    
}

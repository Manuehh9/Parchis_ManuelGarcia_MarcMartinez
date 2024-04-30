package com.Manuel_Garcia.gestionproductos.backend.presentation.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.Manuel_Garcia.gestioToposServices.backend.business.services.TopoService;
import com.Manuel_Garcia.gestionproductos.backend.presentation.config.RespuestaError;
import com.Manuel_Garcia.gestiontopos.backend.business.model.Topo;

@RestController
public class TopoController {

    @Autowired
    private TopoService topoService;
    
    @GetMapping("/topos")
    public List<Topo> getAll(){
        return topoService.getAll();
    }

    @GetMapping("/topos/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) {
        
        if(id > 500) {
            throw new RuntimeException("El número " + id + " no es válido.");
        }
        
        Optional<Topo> optional = topoService.read(id);
        
        if (optional.isEmpty()) {
            RespuestaError respuestaError = new RespuestaError("No se encuentra el topo con id " + id);
            return new ResponseEntity<>(respuestaError, HttpStatus.NOT_FOUND);
        }
        
        return ResponseEntity.ok(optional.get());
    }
    
    // ****************************************************
    //
    // Gestión de excepciones
    //
    // ****************************************************
    
    @ExceptionHandler({IllegalArgumentException.class, ClassCastException.class})
    public ResponseEntity<?> gestor1(Exception e){
        return ResponseEntity.badRequest().body(new RespuestaError(e.getMessage()));
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> gestor2(Exception e){
        return ResponseEntity.badRequest().body(new RespuestaError(e.getMessage()));
    }
    
}

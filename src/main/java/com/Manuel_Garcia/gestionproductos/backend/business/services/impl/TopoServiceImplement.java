package com.Manuel_Garcia.gestionproductos.backend.business.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.Manuel_Garcia.gestioToposServices.backend.business.services.TopoService;
import com.Manuel_Garcia.gestiontopos.backend.business.model.Topo;

@Service
public class TopoServiceImplement implements TopoService {

    private final TreeMap<Long, Topo> TOPOS = new TreeMap<>();
    
    public TopoServiceImplement() {
        init();
    }
    
    @Override
    public Long create(Topo topo) {
        Long id = TOPOS.lastKey() + 1;
        topo.setId(id);
        TOPOS.put(id, topo);
        return id;
    }

    @Override
    public Optional<Topo> read(Long id) {
        return Optional.ofNullable(TOPOS.get(id));
    }

    @Override
    public List<Topo> getAll() {
        return new ArrayList<>(TOPOS.values());
    }
    
    // ***************************************************************
    //
    // Private Methods
    //
    // ***************************************************************

    private void init() {
        Topo t1 = new Topo();
        t1.setId(1L);
        t1.setNombre("Topo1");
        t1.setDescripcion("Topo explorador");
        t1.setPeso(0.5);
        t1.setColor("Gris");
        t1.setFechaNacimiento(new Date(100000000L));
        t1.setHambriento(true);

        Topo t2 = new Topo();
        t2.setId(2L);
        t2.setNombre("Topo2");
        t2.setDescripcion("Topo constructor");
        t2.setPeso(0.6);
        t2.setColor("Marrón");
        t2.setFechaNacimiento(new Date(100000100L));
        t2.setHambriento(false);

        Topo t3 = new Topo();
        t3.setId(3L);
        t3.setNombre("Topo3");
        t3.setDescripcion("Topo minero");
        t3.setPeso(0.7);
        t3.setColor("Negro");
        t3.setFechaNacimiento(new Date(100000200L));
        t3.setHambriento(true);
        
        TOPOS.put(t1.getId(), t1);
        TOPOS.put(t2.getId(), t2);
        TOPOS.put(t3.getId(), t3);
    }
}

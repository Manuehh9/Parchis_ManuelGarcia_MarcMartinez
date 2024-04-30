package com.Manuel_Garcia.gestioTopos;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Manuel_Garcia.gestioToposServices.backend.business.services.TopoService;
import com.Manuel_Garcia.gestiontopos.backend.business.model.Topo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TopoServiceImplementTests {

    @Autowired
    private TopoService topoService;

    @Test
    void createAndGetTopo() {
        Topo topo = new Topo();
        topo.setNombre("Topo4");
        topo.setDescripcion("Topo explorador");
        topo.setPeso(0.5);
        topo.setColor("Gris");
        topo.setFechaNacimiento(new Date(100000300L));
        topo.setHambriento(true);

        Long topoId = topoService.create(topo);
        assertNotNull(topoId, "El ID del topo creado no debería ser nulo");

        Optional<Topo> createdTopoOptional = topoService.read(topoId);
        assertTrue(createdTopoOptional.isPresent(), "El topo creado debería estar presente en la lista de topos");

        Topo createdTopo = createdTopoOptional.get();
        assertEquals(topo.getNombre(), createdTopo.getNombre(), "El nombre del topo debería ser igual al nombre especificado");
        assertEquals(topo.getDescripcion(), createdTopo.getDescripcion(), "La descripción del topo debería ser igual a la descripción especificada");
        assertEquals(topo.getPeso(), createdTopo.getPeso(), "El peso del topo debería ser igual al peso especificado");
        assertEquals(topo.getColor(), createdTopo.getColor(), "El color del topo debería ser igual al color especificado");
        assertEquals(topo.getFechaNacimiento(), createdTopo.getFechaNacimiento(), "La fecha de nacimiento del topo debería ser igual a la fecha especificada");
        assertEquals(topo.isHambriento(), createdTopo.isHambriento(), "El estado de hambre del topo debería ser igual al estado especificado");
    }

    @Test
    void getAllTopos() {
        List<Topo> topos = topoService.getAll();
        assertNotNull(topos, "La lista de topos no debería ser nula");
        assertFalse(topos.isEmpty(), "La lista de topos no debería estar vacía");
        assertEquals(3, topos.size(), "La lista de topos debería contener 3 elementos");
    }
}

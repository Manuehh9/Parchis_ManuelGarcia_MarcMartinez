package com.Manuel_Garcia.gestioToposServices.backend.business.services;

import java.util.List;
import java.util.Optional;

import com.Manuel_Garcia.gestiontopos.backend.business.model.Topo;

public interface TopoService {

	Long create(Topo topo);	    // C
	Optional<Topo> read(Long id);   // R
	
	List<Topo> getAll();
}

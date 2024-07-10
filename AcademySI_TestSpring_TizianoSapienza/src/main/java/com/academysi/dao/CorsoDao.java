package com.academysi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.academysi.model.Categoria;
import com.academysi.model.Corso;


import java.util.List;


@Repository
public interface CorsoDao extends CrudRepository<Corso, Integer> {
	
    // Query methods
    boolean existsByNomeCorso(String nomeCorso);
    
    //List<Corso> searchCorso(String nome, Categoria categoria);
}

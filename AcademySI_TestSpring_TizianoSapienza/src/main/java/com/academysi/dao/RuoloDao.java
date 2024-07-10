package com.academysi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.academysi.model.Ruolo;
import com.academysi.model.Tipologia;

@Repository
public interface RuoloDao extends CrudRepository<Ruolo, Integer> {

    boolean existsByTipologia(Tipologia tipologia);
}
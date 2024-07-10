package com.academysi.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.academysi.dto.CategoriaDto;
import com.academysi.model.Categoria;

@Repository
public interface CategoriaDao extends CrudRepository<Categoria, Integer> {
    
    List<CategoriaDto> getCategorie();
}
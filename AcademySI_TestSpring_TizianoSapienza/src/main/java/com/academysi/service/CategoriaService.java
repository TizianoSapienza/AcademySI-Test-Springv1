package com.academysi.service;

import java.util.List;

import com.academysi.dto.CategoriaDto;
import com.academysi.exceptions.UnauthorizedException;
import com.academysi.model.Categoria;

import javassist.tools.rmi.ObjectNotFoundException;

public interface CategoriaService {

    void registerCategoria(Categoria categoria);

    Categoria getCategoriaById(int id);

    void updateCategoriaData(Categoria categoria);

    List<CategoriaDto> getCategorie();

    void deleteCategoriaById(int id) throws UnauthorizedException, ObjectNotFoundException;
    
}
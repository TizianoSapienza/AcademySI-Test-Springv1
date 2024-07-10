package com.academysi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academysi.dao.CategoriaDao;
import com.academysi.dto.CategoriaDto;
import com.academysi.exceptions.UnauthorizedException;
import com.academysi.model.Categoria;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaDao categoriaDao;

    @Override
    public void registerCategoria(Categoria categoria) {
        categoriaDao.save(categoria);
    }

    @Override
    public Categoria getCategoriaById(int id) {
        Optional<Categoria> categoriaOptionalDb = categoriaDao.findById(id);

        if (!categoriaOptionalDb.isPresent()) {
            return new Categoria();
        }

        return categoriaOptionalDb.get();
    }

    @Override
    public void updateCategoriaData(Categoria categoria) {
        Optional<Categoria> categoriaOptionalDb = categoriaDao.findById(categoria.getId());

        if (categoriaOptionalDb.isPresent()) {
            Categoria categoriaDb = categoriaOptionalDb.get();
            categoriaDb.setNomeCategoria(categoria.getNomeCategoria());
            categoriaDao.save(categoriaDb);
        }
    }

    @Override
    public void deleteCategoriaById(int id) throws UnauthorizedException, ObjectNotFoundException {
    	Optional<Categoria> categoriaOpt = categoriaDao.findById(id);
    	if(!categoriaOpt.isEmpty()) {
    		Categoria categoria = categoriaOpt.get();
    		if(!categoria.getCorsi().isEmpty()) {
    			categoriaDao.delete(categoria);
    		} else {
    			throw new UnauthorizedException();
    		}
    	} else 
    		throw new ObjectNotFoundException(null);
    }
    


	@Override
	public List<CategoriaDto> getCategorie() {
		
		List<Categoria> categorie = (List<Categoria>) categoriaDao.findAll();
		List<CategoriaDto> categorieDto = new ArrayList<>();
		
		ModelMapper modelMapper = new ModelMapper();
		
		for(Categoria categoria : categorie) {
			CategoriaDto categoriaDto = new CategoriaDto();
			modelMapper.map(categoria, categoriaDto);
			
			categorieDto.add(categoriaDto);
		}
		return categorieDto;
	}

}

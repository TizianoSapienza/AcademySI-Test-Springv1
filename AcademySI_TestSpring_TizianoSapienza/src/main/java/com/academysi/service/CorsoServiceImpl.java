package com.academysi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academysi.dao.CategoriaDao;
import com.academysi.dao.CorsoDao;
import com.academysi.model.Categoria;
import com.academysi.model.Corso;
import com.academysi.repository.CorsoDAO;


@Service
public class CorsoServiceImpl implements CorsoService {

    @Autowired
    private CorsoDao corsoDao;
    
    @Autowired
    private CategoriaDao categoriaDao;
    
    @Autowired
    private CorsoDAO corsoDAO;

    @Override
    public void registerCorso(Corso corso) {
        corsoDao.save(corso);
    }

    @Override
    public Corso getCorsoById(int id) {
        Optional<Corso> corsoOptionalDb = corsoDao.findById(id);

        if (!corsoOptionalDb.isPresent()) {
            return new Corso();
        }

        return corsoOptionalDb.get();
    }

    @Override
    public void updateCorsoData(Corso corso) {
        Optional<Corso> corsoOptionalDb = corsoDao.findById(corso.getId());

        if (corsoOptionalDb.isPresent()) {

            // Extract the object and insert it into a course object
            Corso corsoDb = corsoOptionalDb.get();

            // Update data
            corsoDb.setNomeCorso(corso.getNomeCorso());
            corsoDb.setDescrizioneBreve(corso.getDescrizioneBreve());
            corsoDb.setDescrizioneCompleta(corso.getDescrizioneCompleta());
            corsoDb.setDurata(corso.getDurata());
            corsoDb.setCategoria(corso.getCategoria());

            // Save the updated object
            corsoDao.save(corsoDb);
        }
    }

    @Override
    public List<Corso> getCorsi() {
        return (List<Corso>) corsoDao.findAll();
    }

    @Override
    public void deleteCorsoById(int id) {
        Optional<Corso> corsoOptional = corsoDao.findById(id);

        if (corsoOptional.isPresent()) {
            corsoDao.deleteById(id);
        }
    }
    
    public List<Corso> findCorsoByCategoria(int idCa){
        Optional<Categoria> categoriaEntity = categoriaDao.findById(idCa);
        if(categoriaEntity.isPresent()) {      
          List<Corso> listaCorsi = corsoDAO.findByCategoria(categoriaEntity.get());
          return listaCorsi;
        }
        else {
          return new ArrayList<>();
        }
        
      }
      
      @Override
      public void deleteCorsoByCategory(int idCa) {
        List<Corso> listaCorsi = findCorsoByCategoria(idCa);
        for (Corso corso : listaCorsi) {
          corsoDAO.delete(corso);
        }
      }

}

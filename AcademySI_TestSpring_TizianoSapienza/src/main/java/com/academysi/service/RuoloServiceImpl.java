package com.academysi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academysi.dao.RuoloDao;
import com.academysi.model.Ruolo;
import com.academysi.model.Tipologia;

@Service
public class RuoloServiceImpl implements RuoloService {

    @Autowired
    private RuoloDao ruoloDao;

    @Override
    public void registerRuolo(Ruolo ruolo) {
        ruoloDao.save(ruolo);
    }

    @Override
    public Ruolo getRuoloById(int id) {
        Optional<Ruolo> ruoloOptionalDb = ruoloDao.findById(id);

        if (!ruoloOptionalDb.isPresent()) {
            return new Ruolo();
        }

        return ruoloOptionalDb.get();
    }

    @Override
    public void updateRuoloData(Ruolo ruolo) {
        Optional<Ruolo> ruoloOptionalDb = ruoloDao.findById(ruolo.getId());

        if (ruoloOptionalDb.isPresent()) {
            Ruolo ruoloDb = ruoloOptionalDb.get();
            ruoloDb.setTipologia(ruolo.getTipologia());
            ruoloDao.save(ruoloDb);
        }
    }

    @Override
    public List<Ruolo> getRuoli() {
        return (List<Ruolo>) ruoloDao.findAll();
    }

    @Override
    public void deleteRuoloById(int id) {
        ruoloDao.deleteById(id);
    }

    @Override
    public boolean existRuoloByTipologia(Tipologia tipologia) {
        return ruoloDao.existsByTipologia(tipologia);
    }
}
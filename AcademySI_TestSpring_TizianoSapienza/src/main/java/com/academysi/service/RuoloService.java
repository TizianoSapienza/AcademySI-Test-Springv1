package com.academysi.service;

import java.util.List;

import com.academysi.model.Ruolo;
import com.academysi.model.Tipologia;

public interface RuoloService {

    void registerRuolo(Ruolo ruolo);

    Ruolo getRuoloById(int id);

    void updateRuoloData(Ruolo ruolo);

    List<Ruolo> getRuoli();

    void deleteRuoloById(int id);

    boolean existRuoloByTipologia(Tipologia tipologia);
}
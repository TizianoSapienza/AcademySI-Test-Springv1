package com.academysi.service;

import java.util.List;
import com.academysi.model.Corso;

public interface CorsoService {

    void registerCorso(Corso corso);

    Corso getCorsoById(int id);

    void updateCorsoData(Corso corso);

    List<Corso> getCorsi();

    void deleteCorsoById(int id);

	void deleteCorsoByCategory(int idCa);
}
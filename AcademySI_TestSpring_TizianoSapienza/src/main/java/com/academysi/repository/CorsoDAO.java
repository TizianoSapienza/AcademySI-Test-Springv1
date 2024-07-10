package com.academysi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academysi.model.Categoria;
import com.academysi.model.Corso;

@Repository
public interface CorsoDAO extends JpaRepository<Corso, Integer> {
  public List<Corso> findByCategoria(Categoria categoria);
}
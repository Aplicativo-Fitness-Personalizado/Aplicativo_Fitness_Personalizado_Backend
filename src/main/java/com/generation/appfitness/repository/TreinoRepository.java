package com.generation.appfitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.appfitness.model.Treino;

public interface TreinoRepository extends JpaRepository<Treino, Long> {

	public List<Treino> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);
}

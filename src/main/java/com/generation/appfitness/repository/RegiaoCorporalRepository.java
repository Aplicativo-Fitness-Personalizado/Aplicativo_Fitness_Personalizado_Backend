package com.generation.appfitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.appfitness.model.RegiaoCorporal;



public interface RegiaoCorporalRepository extends JpaRepository<RegiaoCorporal, Long> {

	   public List<RegiaoCorporal> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);

	}
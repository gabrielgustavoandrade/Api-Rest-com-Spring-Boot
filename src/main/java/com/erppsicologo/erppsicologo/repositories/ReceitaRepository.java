package com.erppsicologo.erppsicologo.repositories;

import com.erppsicologo.erppsicologo.entities.Receita;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Integer> {
    
}
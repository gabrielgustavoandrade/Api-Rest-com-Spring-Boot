package com.erppsicologo.erppsicologo.repositories;

import com.erppsicologo.erppsicologo.entities.TipoRecebimento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRecebimentoRepository extends JpaRepository<TipoRecebimento, Integer>{
    
}
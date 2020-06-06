package com.erppsicologo.erppsicologo.repositories;

import com.erppsicologo.erppsicologo.entities.MotivoPagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotivoPagamentoRepository extends JpaRepository<MotivoPagamento, Integer>{
    
}
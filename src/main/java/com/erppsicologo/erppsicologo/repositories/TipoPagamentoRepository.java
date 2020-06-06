package com.erppsicologo.erppsicologo.repositories;

import com.erppsicologo.erppsicologo.entities.TipoPagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPagamentoRepository extends JpaRepository<TipoPagamento, Integer> {
    
}
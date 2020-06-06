package com.erppsicologo.erppsicologo.Interface;

import java.util.List;

import com.erppsicologo.erppsicologo.entities.MotivoPagamento;

import org.hibernate.service.spi.ServiceException;

public interface MotivoPagamentoService {
    
    
    MotivoPagamento gravar(MotivoPagamento motivoPagamento) throws ServiceException;

    void remover(Integer id) throws ServiceException;

    MotivoPagamento buscar(Integer id) throws ServiceException;

    List<MotivoPagamento> buscarTodos() throws ServiceException;

}
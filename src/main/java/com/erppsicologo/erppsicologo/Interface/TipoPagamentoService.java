package com.erppsicologo.erppsicologo.Interface;

import java.util.List;

import com.erppsicologo.erppsicologo.entities.TipoPagamento;

import org.hibernate.service.spi.ServiceException;

public interface TipoPagamentoService {
    
    
    TipoPagamento gravar(TipoPagamento tipoPagamento) throws ServiceException;

    void remover(Integer id) throws ServiceException;

    TipoPagamento buscar(Integer id) throws ServiceException;

    List<TipoPagamento> buscarTodos() throws ServiceException;

}
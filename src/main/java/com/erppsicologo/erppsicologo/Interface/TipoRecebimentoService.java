package com.erppsicologo.erppsicologo.Interface;

import java.util.List;

import com.erppsicologo.erppsicologo.entities.TipoRecebimento;

import org.hibernate.service.spi.ServiceException;

public interface TipoRecebimentoService {
    
    TipoRecebimento gravar(TipoRecebimento tipoRecebimento) throws ServiceException;

    void remover(Integer id) throws ServiceException;

    TipoRecebimento buscar(Integer id) throws ServiceException;

    List<TipoRecebimento> buscarTodos() throws ServiceException;

}
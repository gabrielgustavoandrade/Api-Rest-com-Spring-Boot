package com.erppsicologo.erppsicologo.Interface;

import java.util.List;

import com.erppsicologo.erppsicologo.dto.ReceitaDTO;
import com.erppsicologo.erppsicologo.dto.ReceitaOnlyIdAgendaDTO;

import org.hibernate.service.spi.ServiceException;

public interface ReceitaService {
    
    
    ReceitaDTO gravar(ReceitaDTO receitaDTO) throws ServiceException;

    void remover(Integer id) throws ServiceException;

    ReceitaDTO buscar(Integer id) throws ServiceException;

    ReceitaDTO alterar(Integer id, ReceitaDTO receitaDTO) throws ServiceException;

    List<ReceitaOnlyIdAgendaDTO> buscarTodos() throws ServiceException;

}
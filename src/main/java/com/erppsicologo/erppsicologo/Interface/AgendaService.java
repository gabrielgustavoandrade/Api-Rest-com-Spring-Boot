package com.erppsicologo.erppsicologo.Interface;

import java.util.List;

import com.erppsicologo.erppsicologo.dto.AgendaDTO;
import com.erppsicologo.erppsicologo.dto.AgendaDTOSemPsicologo;

import org.hibernate.service.spi.ServiceException;

public interface AgendaService {
    
    AgendaDTOSemPsicologo gravar(Integer idPaciente, AgendaDTO AgendaDTO, String emailPsicologo) throws Exception;

    void remover(Integer id) throws ServiceException;

    List<AgendaDTOSemPsicologo> buscar(String subject) throws ServiceException;

    AgendaDTO alterar(Integer id, AgendaDTO agendaDTO) throws ServiceException;

    List<AgendaDTO> buscarTodos() throws ServiceException;

    List<AgendaDTOSemPsicologo> buscarAgendaPacientesByPsicologo(Integer idPaciente, String emailPsicologo, Integer limit, Integer page) throws ServiceException;
}
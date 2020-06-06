package com.erppsicologo.erppsicologo.Interface;

import java.util.List;

import com.erppsicologo.erppsicologo.dto.PacienteDTO;
import com.erppsicologo.erppsicologo.dto.PacienteDTOSemPsicologo;

import org.hibernate.service.spi.ServiceException;

public interface PacienteService {
    
    
    PacienteDTOSemPsicologo gravar(PacienteDTO PacienteDTO, String email) throws ServiceException;

    Boolean remover(Integer id) throws ServiceException;

    PacienteDTOSemPsicologo alterar(Integer id, PacienteDTO pacienteDTO, String subject) throws ServiceException;

    PacienteDTOSemPsicologo buscar(Integer id) throws ServiceException;

    List<PacienteDTOSemPsicologo> buscarTodos() throws ServiceException;

    List<PacienteDTOSemPsicologo> findAllByPsicolgoEmail(String email, Integer limit, Integer page) throws ServiceException;

	PacienteDTOSemPsicologo findByEmail(String email);

}
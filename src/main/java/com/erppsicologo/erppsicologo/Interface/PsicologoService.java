package com.erppsicologo.erppsicologo.Interface;

import java.util.List;

import com.erppsicologo.erppsicologo.dto.PsicologoDTO;
import com.erppsicologo.erppsicologo.dto.PsicologoDTOSemPaciente;

import org.hibernate.service.spi.ServiceException;

public interface PsicologoService {
    
    PsicologoDTO gravar(PsicologoDTO psicologoDTO) throws ServiceException;

    Boolean remover(Integer id) throws ServiceException;

    PsicologoDTO buscar(Integer id) throws ServiceException;

    PsicologoDTOSemPaciente alterar(String subject, PsicologoDTO psicologoDTO) throws ServiceException;

    List<PsicologoDTOSemPaciente> buscarTodos() throws ServiceException;

    PsicologoDTO findLogged(String subject)  throws ServiceException;

    void addUserStandard() throws ServiceException;

	PsicologoDTO findByEmail(String subject) throws ServiceException;

}
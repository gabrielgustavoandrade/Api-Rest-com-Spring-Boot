package com.erppsicologo.erppsicologo.impls;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.erppsicologo.erppsicologo.Interface.AgendaService;
import com.erppsicologo.erppsicologo.dto.AgendaDTO;
import com.erppsicologo.erppsicologo.dto.AgendaDTOSemPsicologo;
import com.erppsicologo.erppsicologo.dto.PacienteDTOSemPsicologo;
import com.erppsicologo.erppsicologo.entities.Agenda;
import com.erppsicologo.erppsicologo.entities.Paciente;
import com.erppsicologo.erppsicologo.entities.Psicologo;
import com.erppsicologo.erppsicologo.repositories.AgendaRepository;
import com.erppsicologo.erppsicologo.repositories.PacienteRepository;
import com.erppsicologo.erppsicologo.repositories.PsicologoRepository;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaServiceImpl implements AgendaService {

    @Autowired
    private AgendaRepository repository;
    @Autowired
    private PsicologoRepository psiRepository;
    @Autowired
    private PacienteRepository paRepository;

    @Override
    public AgendaDTOSemPsicologo gravar(Integer idPaciente, final AgendaDTO agendaDTO, String emailPsicologo)
            throws Exception {
        try {
            Psicologo psicologo = psiRepository.findByEmail(emailPsicologo);
            agendaDTO.setPsicologo(psicologo);
            Optional<Paciente> paciente = paRepository.findById(idPaciente);
            if (paciente.isPresent()) {
                agendaDTO.setPaciente(paciente.get());
            } else {
                throw new Exception("Paciente inexistente");
            }
            Agenda entidade = toEntidade(new Agenda(), agendaDTO);
            entidade = repository.save(entidade);
            PacienteDTOSemPsicologo pacienteDTO = new PacienteDTOSemPsicologo(entidade.getPaciente());
            return new AgendaDTOSemPsicologo(entidade, pacienteDTO);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public AgendaDTO alterar(Integer id, AgendaDTO agendaDTO) throws ServiceException {
        Optional<Agenda> entidade = repository.findById(id);
        if (entidade.isPresent()) {
            if (agendaDTO.getPsicologo() == null) {
                agendaDTO.setPsicologo(entidade.get().getPsicologo());
            }
            Agenda agenda = toEntidade(entidade.get(), agendaDTO);
            return new AgendaDTO(repository.save(agenda));
        }
        return new AgendaDTO();
    }

    @Override
    public void remover(final Integer id) throws ServiceException {
        final Agenda entity = new Agenda();
        entity.setId(id);
        repository.delete(entity);
    }

    @Override
    public List<AgendaDTOSemPsicologo> buscar(String subject) throws ServiceException {
        List<Agenda> agenda = repository.findByPsicologo(subject);
        List<AgendaDTOSemPsicologo> agendaDTO = new ArrayList<AgendaDTOSemPsicologo>();
        for (Agenda entidade : agenda) {
            PacienteDTOSemPsicologo paciente = new PacienteDTOSemPsicologo(entidade.getPaciente());
            AgendaDTOSemPsicologo agendaDTO2 = new AgendaDTOSemPsicologo(entidade, paciente);
            agendaDTO.add(agendaDTO2);
        }
        return agendaDTO;
    }

    @Override
    public List<AgendaDTO> buscarTodos() throws ServiceException {
        final List<AgendaDTO> agendaDTO = toDTO(repository.findAll());
        return agendaDTO;
    }

    @Override
    public List<AgendaDTOSemPsicologo> buscarAgendaPacientesByPsicologo(Integer idPaciente, String emailPsicologo,
            Integer limit, Integer page) {
        List<AgendaDTOSemPsicologo> agendaDTO = toDTOSemPsicologo(
                repository.buscarAgendaPacientesByPsicologo(idPaciente, emailPsicologo, limit, page));
        return agendaDTO;
    }

    private Agenda toEntidade(final Agenda entidade, final AgendaDTO agendaDTO) {
        entidade.setId(agendaDTO.getId());
        entidade.setData(agendaDTO.getData());
        entidade.setDataAgendamento(agendaDTO.getDataAgendamento());
        entidade.setPaciente(agendaDTO.getPaciente());
        entidade.setPsicologo(agendaDTO.getPsicologo());
        entidade.setReceita(agendaDTO.getReceita());
        entidade.setConsultaConfirmada(agendaDTO.getConsultaConfirmada());
        return entidade;
    }

    private List<AgendaDTO> toDTO(final List<Agenda> entidades) {
        List<AgendaDTO> agendaDTO = new ArrayList<AgendaDTO>();
        for (Agenda agenda : entidades) {
            AgendaDTO agendaDTO2 = new AgendaDTO(agenda);
            agendaDTO.add(agendaDTO2);
        }
        return agendaDTO;
    }

    private List<AgendaDTOSemPsicologo> toDTOSemPsicologo(final List<Agenda> entidades) {
        List<AgendaDTOSemPsicologo> agendaDTO = new ArrayList<AgendaDTOSemPsicologo>();
        for (Agenda agenda : entidades) {
            PacienteDTOSemPsicologo pacienteDTO = new PacienteDTOSemPsicologo(agenda.getPaciente());
            AgendaDTOSemPsicologo agendaDTO2 = new AgendaDTOSemPsicologo(agenda, pacienteDTO);
            agendaDTO.add(agendaDTO2);
        }
        return agendaDTO;
    }
}
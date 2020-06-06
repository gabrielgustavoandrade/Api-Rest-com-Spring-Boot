package com.erppsicologo.erppsicologo.impls;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.erppsicologo.erppsicologo.Interface.PacienteService;
import com.erppsicologo.erppsicologo.dto.PacienteDTO;
import com.erppsicologo.erppsicologo.dto.PacienteDTOSemPsicologo;
import com.erppsicologo.erppsicologo.entities.Paciente;
import com.erppsicologo.erppsicologo.entities.Psicologo;
import com.erppsicologo.erppsicologo.repositories.PacienteRepository;
import com.erppsicologo.erppsicologo.repositories.PsicologoRepository;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    PacienteRepository repository;

    @Autowired
    PsicologoRepository repositoryPsicologo;

    @Override
    public PacienteDTOSemPsicologo gravar(PacienteDTO pacienteDTO, String email) throws ServiceException {
        Psicologo psicologo = repositoryPsicologo.findByEmail(email);
        Paciente entidade = toEntidade(new Paciente(), pacienteDTO);
        entidade.setPsicologo(psicologo);
        Paciente paciente = repository.save(entidade);
        return new PacienteDTOSemPsicologo(paciente);
    }

    @Override
    public Boolean remover(Integer id) throws ServiceException {
        Optional<Paciente> entity = repository.findById(id);
        if (entity.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public PacienteDTOSemPsicologo buscar(Integer id) throws ServiceException {
        Optional<Paciente> entity = repository.findById(id);
        return entity.isEmpty() ? new PacienteDTOSemPsicologo() : new PacienteDTOSemPsicologo(entity.get());
    }

    @Override
    public PacienteDTOSemPsicologo alterar(Integer id, PacienteDTO pacienteDTO, String email) throws ServiceException {
        Psicologo psicologo = repositoryPsicologo.findByEmail(email);
        Optional<Paciente> entidade = repository.findById(id);
        if (entidade.isPresent()) {
            Paciente paciente = toEntidade(entidade.get(), pacienteDTO);
            paciente.setPsicologo(psicologo);
            paciente.setId(id);
            paciente = repository.save(paciente);
            return new PacienteDTOSemPsicologo(paciente);
        }
        return new PacienteDTOSemPsicologo();
    }

    @Override
    public List<PacienteDTOSemPsicologo> buscarTodos() throws ServiceException {
        final List<PacienteDTOSemPsicologo> pacienteDTOSemPsicologo = toDTO2(repository.findAll());
        return pacienteDTOSemPsicologo;
    }

    @Override
    public List<PacienteDTOSemPsicologo> findAllByPsicolgoEmail(String email, Integer limit, Integer page) throws ServiceException {
        final List<PacienteDTOSemPsicologo> PacienteDTOSemPsicologo = toDTO2(repository.findAllByPsicolgoEmail(email, limit, page));
        return PacienteDTOSemPsicologo;
    }

    @Override
    public PacienteDTOSemPsicologo findByEmail(String email) throws ServiceException {
        Paciente paciente = repository.findByEmail(email);
        if (paciente != null) {
            final PacienteDTOSemPsicologo PacienteDTOSemPsicologo = new PacienteDTOSemPsicologo(paciente);
            return PacienteDTOSemPsicologo;
        } else
            return new PacienteDTOSemPsicologo();
    }

    private Paciente toEntidade(final Paciente entidade, final PacienteDTO pacienteDTO) {
        entidade.setId(pacienteDTO.getId());
        entidade.setAgenda(pacienteDTO.getAgenda());
        entidade.setNome(pacienteDTO.getNome());
        entidade.setSobrenome(pacienteDTO.getSobrenome());
        entidade.setCpf(pacienteDTO.getCpf());
        entidade.setPsicologo(pacienteDTO.getPsicologo());
        entidade.setValor(pacienteDTO.getValor());
        entidade.setSenha(pacienteDTO.getSenha());
        entidade.setCelular(pacienteDTO.getCelular());
        entidade.setTelefone(pacienteDTO.getTelefone());
        entidade.setAgenda(pacienteDTO.getAgenda());
        entidade.setDataNascimento(pacienteDTO.getDataNascimento());
        entidade.setEmail(pacienteDTO.getEmail());
        return entidade;
    }

    private List<PacienteDTOSemPsicologo> toDTO2(final List<Paciente> entidades) {
        List<PacienteDTOSemPsicologo> dto = new ArrayList<PacienteDTOSemPsicologo>();
        for (Paciente paciente : entidades) {
            PacienteDTOSemPsicologo dto2 = new PacienteDTOSemPsicologo(paciente);
            dto.add(dto2);
        }
        return dto;
    }

    // private List<PacienteDTO> toDTO(final List<Paciente> entidades) {
    // List<PacienteDTO> pacienteDTO = new ArrayList<PacienteDTO>();
    // for (Paciente paciente : entidades) {
    // PacienteDTO pacienteDTO2 = new PacienteDTO(paciente);
    // pacienteDTO.add(pacienteDTO2);
    // }
    // return pacienteDTO;
    // }

}
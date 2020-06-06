package com.erppsicologo.erppsicologo.impls;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.erppsicologo.erppsicologo.Interface.PsicologoService;
import com.erppsicologo.erppsicologo.dto.PsicologoDTO;
import com.erppsicologo.erppsicologo.dto.PsicologoDTOSemPaciente;
import com.erppsicologo.erppsicologo.entities.Psicologo;
import com.erppsicologo.erppsicologo.repositories.PsicologoRepository;
import com.erppsicologo.erppsicologo.service.TokenService;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PsicologoServiceImpl implements PsicologoService {

    @Autowired
    PsicologoRepository repository;
    TokenService tokenService;

    public PsicologoServiceImpl(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public void addUserStandard() {
        Psicologo psicologo = repository.findByEmail("admin@admin.com");

        if (psicologo == null) {
            PsicologoDTO psicologoDTO = new PsicologoDTO();
            psicologoDTO.setEmail("admin@admin.com");
            psicologoDTO.setSenha("admin");
            gravar(psicologoDTO);
        }
    }

    @Override
    public PsicologoDTO gravar(PsicologoDTO psicologoDTO) throws ServiceException {
        Psicologo entidade = toEntidade(new Psicologo(), psicologoDTO);
        repository.save(entidade);
        psicologoDTO = new PsicologoDTO(entidade);
        return psicologoDTO;
    }

    @Override
    public Boolean remover(Integer id) throws ServiceException {
        Optional<Psicologo> entity = repository.findById(id);
        if (entity.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public PsicologoDTO buscar(Integer id) throws ServiceException {
        Optional<Psicologo> entity = repository.findById(id);
        return entity.isEmpty() ? new PsicologoDTO() : new PsicologoDTO(entity.get());
    }

    @Override
    public PsicologoDTOSemPaciente alterar(String email, PsicologoDTO psicologoDTO) {
        Psicologo entidade = repository.findByEmail(email);
        psicologoDTO.setId(entidade.getId());
        if (!entidade.getEmail().isBlank()) {
            Psicologo psicologo = toEntidade(entidade, psicologoDTO);
            psicologo.setId(entidade.getId());
            repository.save(psicologo);
            psicologo = repository.findByEmail(email);
            return new PsicologoDTOSemPaciente(psicologo);
        }
        return new PsicologoDTOSemPaciente();
    }

    @Override
    public List<PsicologoDTOSemPaciente> buscarTodos() throws ServiceException {
        final List<PsicologoDTOSemPaciente> PsicologoDTOSemPaciente = toDTOSemPaciente(repository.findAll());
        return PsicologoDTOSemPaciente;
    }

    @Override
    public PsicologoDTO findLogged(String email) throws ServiceException {
        Psicologo psicologo = repository.findLogged(email);
        if (psicologo != null) {
            final PsicologoDTO psicologoDTO = toOneDTO(psicologo);
            return psicologoDTO;
        } else
            return new PsicologoDTO();
    }

    @Override
    public PsicologoDTO findByEmail(String email) throws ServiceException {
        Psicologo psicologo = repository.findByEmail(email);
        if (psicologo != null) {
            final PsicologoDTO psicologoDTO = toOneDTO(psicologo);
            return psicologoDTO;
        } else
            return new PsicologoDTO();
    }

    private Psicologo toEntidade(final Psicologo entidade, final PsicologoDTO psicologoDTO) {
        entidade.setId(psicologoDTO.getId());
        entidade.setNome(psicologoDTO.getNome());
        entidade.setSobrenome(psicologoDTO.getSobrenome());
        entidade.setCpf(psicologoDTO.getCpf());
        entidade.setEmail(psicologoDTO.getEmail());
        entidade.setAgenda(psicologoDTO.getAgenda());
        entidade.setPaciente(psicologoDTO.getPaciente());
        entidade.setSenha(psicologoDTO.getSenha());
        entidade.setCelular(psicologoDTO.getCelular());
        entidade.setTelefone(psicologoDTO.getTelefone());
        return entidade;
    }

    // private List<PsicologoDTO> toDTO(final List<Psicologo> entidades) {
    //     List<PsicologoDTO> psicologoDTO = new ArrayList<PsicologoDTO>();
    //     for (Psicologo psicologo : entidades) {
    //         PsicologoDTO psicologoDTO2 = new PsicologoDTO(psicologo);
    //         psicologoDTO.add(psicologoDTO2);
    //     }
    //     return psicologoDTO;
    // }

    private List<PsicologoDTOSemPaciente> toDTOSemPaciente(final List<Psicologo> entidades) {
        List<PsicologoDTOSemPaciente> psicologoDTOSemPaciente = new ArrayList<PsicologoDTOSemPaciente>();
        for (Psicologo psicologo : entidades) {
            PsicologoDTOSemPaciente psicologoDTOSemPaciente2 = new PsicologoDTOSemPaciente(psicologo);
            psicologoDTOSemPaciente.add(psicologoDTOSemPaciente2);
        }
        return psicologoDTOSemPaciente;
    }

    private PsicologoDTO toOneDTO(Psicologo entidade) {
        PsicologoDTO psicologoDTO = new PsicologoDTO(entidade);
        return psicologoDTO;
    }

}
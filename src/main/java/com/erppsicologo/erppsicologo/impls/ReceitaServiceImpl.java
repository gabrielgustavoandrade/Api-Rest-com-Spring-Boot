package com.erppsicologo.erppsicologo.impls;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.erppsicologo.erppsicologo.Interface.ReceitaService;
import com.erppsicologo.erppsicologo.dto.ReceitaDTO;
import com.erppsicologo.erppsicologo.dto.ReceitaOnlyIdAgendaDTO;
import com.erppsicologo.erppsicologo.entities.Receita;
import com.erppsicologo.erppsicologo.repositories.ReceitaRepository;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceitaServiceImpl implements ReceitaService {

    @Autowired
    ReceitaRepository repository;

    @Override
    public ReceitaDTO gravar(ReceitaDTO receitaDTO) throws ServiceException {
        Receita entidade = toEntidade(new Receita(), receitaDTO);
        repository.save(entidade);
        return receitaDTO;
    }

    @Override
    public void remover(Integer id) throws ServiceException {
        repository.deleteById(id);

    }

    @Override
    public ReceitaDTO buscar(Integer id) throws ServiceException {
        Optional<Receita> entity = repository.findById(id);
        return entity.isEmpty() ? new ReceitaDTO() : new ReceitaDTO(entity.get());
    }

    @Override
    public List<ReceitaOnlyIdAgendaDTO> buscarTodos() throws ServiceException {
        final List<ReceitaOnlyIdAgendaDTO> receitaOnlyIdAgendaDTO = toDTO(repository.findAll());
        return receitaOnlyIdAgendaDTO;
    }

    private Receita toEntidade(final Receita entidade, final ReceitaDTO receitaDTO) {
        entidade.setId(receitaDTO.getId());
        entidade.setValor(receitaDTO.getValor());
        entidade.setDataPagamento(receitaDTO.getDataPagamento());
        entidade.setDataCadastro(receitaDTO.getDataCadastro());
        entidade.setAgenda(receitaDTO.getAgenda());
        return entidade;
    }

    private List<ReceitaOnlyIdAgendaDTO> toDTO(final List<Receita> entidades) {
        List<ReceitaOnlyIdAgendaDTO> receitaOnlyIdAgendaDTO = new ArrayList<ReceitaOnlyIdAgendaDTO>();
        for(Receita receita : entidades){
            ReceitaOnlyIdAgendaDTO receitaDTO2 = new ReceitaOnlyIdAgendaDTO(receita);
            receitaOnlyIdAgendaDTO.add(receitaDTO2);
        }
        return receitaOnlyIdAgendaDTO;
    }

    @Override
    public ReceitaDTO alterar(Integer id, ReceitaDTO receitaDTO) throws ServiceException {
        Optional<Receita> entidade = repository.findById(id);
        if(entidade.isPresent()){
            Receita receita = toEntidade(entidade.get(), receitaDTO);
            return new ReceitaDTO(repository.save(receita));
        }
        throw new ServiceException("receita_nao_encontrada");
    }
    
}
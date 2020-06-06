package com.erppsicologo.erppsicologo.impls;

import java.util.List;
import java.util.Optional;

import com.erppsicologo.erppsicologo.Interface.TipoRecebimentoService;
import com.erppsicologo.erppsicologo.entities.TipoRecebimento;
import com.erppsicologo.erppsicologo.repositories.TipoRecebimentoRepository;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoRecebimentoServiceImpl implements TipoRecebimentoService {

    @Autowired
    TipoRecebimentoRepository repository;

    @Override
    public TipoRecebimento gravar(TipoRecebimento tipoRecebimento) throws ServiceException {
        repository.save(tipoRecebimento);
        return tipoRecebimento;
    }

    @Override
    public void remover(Integer id) throws ServiceException {
        repository.deleteById(id);

    }

    @Override
    public TipoRecebimento buscar(Integer id) throws ServiceException {
        Optional<TipoRecebimento> entity = repository.findById(id);
        return entity.isEmpty() ? new TipoRecebimento() : new TipoRecebimento(entity.get());
        
    }

    @Override
    public List<TipoRecebimento> buscarTodos() throws ServiceException {
        return repository.findAll();
    }
    
}
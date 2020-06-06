package com.erppsicologo.erppsicologo.impls;

import java.util.List;
import java.util.Optional;

import com.erppsicologo.erppsicologo.Interface.TipoPagamentoService;
import com.erppsicologo.erppsicologo.entities.TipoPagamento;
import com.erppsicologo.erppsicologo.repositories.TipoPagamentoRepository;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoPagamentoServiceImpl implements TipoPagamentoService {

    @Autowired
    TipoPagamentoRepository repository;

    @Override
    public TipoPagamento gravar(TipoPagamento tipoPagamento) throws ServiceException {
        repository.save(tipoPagamento);
        return tipoPagamento;
    }

    @Override
    public void remover(Integer id) throws ServiceException {
        repository.deleteById(id);

    }

    @Override
    public TipoPagamento buscar(Integer id) throws ServiceException {
        Optional<TipoPagamento> entity = repository.findById(id);
        return entity.isEmpty() ? new TipoPagamento() : new TipoPagamento(entity.get());
    }

    @Override
    public List<TipoPagamento> buscarTodos() throws ServiceException {
        return repository.findAll();
    }
    
}
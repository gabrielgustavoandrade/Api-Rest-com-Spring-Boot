package com.erppsicologo.erppsicologo.impls;

import java.util.List;
import java.util.Optional;

import com.erppsicologo.erppsicologo.Interface.MotivoPagamentoService;
import com.erppsicologo.erppsicologo.entities.MotivoPagamento;
import com.erppsicologo.erppsicologo.repositories.MotivoPagamentoRepository;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotivoPagamentoServiceImpl implements MotivoPagamentoService {

    @Autowired
    MotivoPagamentoRepository repository;

    @Override
    public MotivoPagamento gravar(MotivoPagamento motivoPagamento) throws ServiceException {
        repository.save(motivoPagamento);
        return motivoPagamento;
    }

    @Override
    public void remover(Integer id) throws ServiceException {
        repository.deleteById(id);
    }

    @Override
    public MotivoPagamento buscar(Integer id) throws ServiceException {
        Optional<MotivoPagamento> entity = repository.findById(id);
        return entity.isEmpty() ? new MotivoPagamento() : new MotivoPagamento(entity.get());
    }

    @Override
    public List<MotivoPagamento> buscarTodos() throws ServiceException {
        return repository.findAll();
    }
    
}
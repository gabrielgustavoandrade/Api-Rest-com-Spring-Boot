package com.erppsicologo.erppsicologo.dto;

import java.util.Date;

import com.erppsicologo.erppsicologo.entities.Agenda;
import com.erppsicologo.erppsicologo.entities.Receita;

public class ReceitaDTO {
    
    private Integer id;
    private Double valor;
    private Date dataPagamento;
    private Date dataCadastro;
    private Agenda agenda;

    public ReceitaDTO(Receita receita){
        setId(receita.getId());
        setValor(receita.getValor());
        setDataPagamento(receita.getDataPagamento());
        setDataCadastro(receita.getDataCadastro());
        setAgenda(receita.getAgenda());
    }

    public ReceitaDTO() {
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }
    
}
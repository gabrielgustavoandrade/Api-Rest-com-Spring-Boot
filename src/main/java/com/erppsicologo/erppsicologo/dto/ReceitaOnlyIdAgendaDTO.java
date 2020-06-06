package com.erppsicologo.erppsicologo.dto;

import java.util.Date;

import com.erppsicologo.erppsicologo.entities.Receita;

public class ReceitaOnlyIdAgendaDTO {
    
    private Integer id;
    private Double valor;
    private Date dataPagamento;
    private Date dataCadastro;
    private Integer agenda;

    public ReceitaOnlyIdAgendaDTO(Receita receita){
        setId(receita.getId());
        setValor(receita.getValor());
        setDataPagamento(receita.getDataPagamento());
        setDataCadastro(receita.getDataCadastro());
        setAgenda(receita.getAgenda().getId());
    }

    public ReceitaOnlyIdAgendaDTO() {
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

    public Integer getAgenda() {
        return agenda;
    }

    public void setAgenda(Integer agenda) {
        this.agenda = agenda;
    }

}
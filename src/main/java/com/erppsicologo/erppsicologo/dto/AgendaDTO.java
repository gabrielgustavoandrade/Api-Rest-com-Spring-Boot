package com.erppsicologo.erppsicologo.dto;

import java.util.Date;

import com.erppsicologo.erppsicologo.entities.Agenda;
import com.erppsicologo.erppsicologo.entities.Paciente;
import com.erppsicologo.erppsicologo.entities.Psicologo;
import com.erppsicologo.erppsicologo.entities.Receita;

public class AgendaDTO {

    private Integer id;
    private Date data;
    private Date dataAgendamento;
    private Psicologo psicologo;
    private Paciente paciente;
    private Receita receita;
    private Boolean consultaConfirmada;

    public AgendaDTO(Agenda agenda){
        setId(agenda.getId());
        setData(agenda.getData());
        setDataAgendamento(agenda.getDataAgendamento());
        setPsicologo(agenda.getPsicologo());
        setPaciente(agenda.getPaciente());
        setReceita(agenda.getReceita());
        setConsultaConfirmada(agenda.getConsultaConfirmada());
    }

    public AgendaDTO() {
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Psicologo getPsicologo() {
        return psicologo;
    }

    public void setPsicologo(Psicologo psicologo) {
        this.psicologo = psicologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public Boolean getConsultaConfirmada() {
        return consultaConfirmada;
    }

    public void setConsultaConfirmada(Boolean consultaConfirmada) {
        this.consultaConfirmada = consultaConfirmada;
    }
}
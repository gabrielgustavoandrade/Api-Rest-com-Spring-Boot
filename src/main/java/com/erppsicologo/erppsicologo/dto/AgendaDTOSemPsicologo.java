package com.erppsicologo.erppsicologo.dto;

import java.util.Date;

import com.erppsicologo.erppsicologo.entities.Agenda;
import com.erppsicologo.erppsicologo.entities.Receita;

public class AgendaDTOSemPsicologo {
    
    private Integer id;
    private Date data;
    private Date dataAgendamento;
    private PacienteDTOSemPsicologo paciente;
    private Receita receita;
    private Boolean consultaConfirmada;

    public AgendaDTOSemPsicologo(Agenda agenda, PacienteDTOSemPsicologo paciente){
        setId(agenda.getId());
        setData(agenda.getData());
        setDataAgendamento(agenda.getDataAgendamento());
        setPaciente(paciente);
        setReceita(agenda.getReceita());
        setConsultaConfirmada(agenda.getConsultaConfirmada());
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

    public PacienteDTOSemPsicologo getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDTOSemPsicologo paciente) {
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
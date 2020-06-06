package com.erppsicologo.erppsicologo.entities;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class Agenda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    private Date data;
    private Date dataAgendamento;
    @Column(columnDefinition = "boolean default false")
    private Boolean consultaConfirmada;
    @ManyToOne
    private Psicologo psicologo;
    @OneToOne
    private Paciente paciente;
    @OneToOne
    private Receita receita;

    public Agenda() {
    }
    
    public Agenda(Agenda agenda) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
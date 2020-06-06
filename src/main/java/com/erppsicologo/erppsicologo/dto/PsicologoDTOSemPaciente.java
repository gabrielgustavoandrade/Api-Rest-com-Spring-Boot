package com.erppsicologo.erppsicologo.dto;

import java.util.List;

import com.erppsicologo.erppsicologo.entities.Agenda;
import com.erppsicologo.erppsicologo.entities.Psicologo;

public class PsicologoDTOSemPaciente {

    private Integer id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String email;
    private List<Agenda> agenda;
    private String celular;
    private String telefone;

    public PsicologoDTOSemPaciente(Psicologo psicologo) {
        this.id = psicologo.getId();
        this.nome = psicologo.getNome();
        this.sobrenome = psicologo.getSobrenome();
        this.cpf = psicologo.getCpf();
        this.email = psicologo.getEmail();
        if(psicologo.getAgenda() != null && !psicologo.getAgenda().isEmpty()){
            this.agenda.addAll(psicologo.getAgenda());
        }
        this.celular = psicologo.getCelular();
        this.telefone = psicologo.getTelefone();
    }

    public PsicologoDTOSemPaciente() {
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Agenda> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<Agenda> agenda) {
        this.agenda = agenda;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
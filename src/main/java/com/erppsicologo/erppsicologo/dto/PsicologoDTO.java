package com.erppsicologo.erppsicologo.dto;

import java.util.List;

import com.erppsicologo.erppsicologo.entities.Agenda;
import com.erppsicologo.erppsicologo.entities.Paciente;
import com.erppsicologo.erppsicologo.entities.Psicologo;

public class PsicologoDTO {

    private Integer id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String email;
    private String senha;
    private List<Agenda> agenda;
    private List<Paciente> paciente;
    private String celular;
    private String telefone;

    public PsicologoDTO(Psicologo psicologo) {
        this.id = psicologo.getId();
        this.nome = psicologo.getNome();
        this.sobrenome = psicologo.getSobrenome();
        this.cpf = psicologo.getCpf();
        this.email = psicologo.getEmail();
        if(psicologo.getAgenda() != null && !psicologo.getAgenda().isEmpty()){
            this.agenda.addAll(psicologo.getAgenda());
        }
        if(psicologo.getPaciente() != null && !psicologo.getPaciente().isEmpty()){
            this.paciente.addAll(psicologo.getPaciente());
        }
        this.celular = psicologo.getCelular();
        this.telefone = psicologo.getTelefone();
    }

    public PsicologoDTO() {
	}

	public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(final String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(final String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(final String senha) {
        this.senha = senha;
    }

    public List<Agenda> getAgenda() {
        return agenda;
    }

    public void setAgenda(final List<Agenda> agenda) {
        this.agenda = agenda;
    }

    public List<Paciente> getPaciente() {
        return paciente;
    }

    public void setPaciente(final List<Paciente> paciente) {
        this.paciente = paciente;
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
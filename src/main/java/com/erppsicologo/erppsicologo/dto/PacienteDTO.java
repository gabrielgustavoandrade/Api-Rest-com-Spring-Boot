package com.erppsicologo.erppsicologo.dto;

import java.util.Date;

import com.erppsicologo.erppsicologo.entities.Agenda;
import com.erppsicologo.erppsicologo.entities.Paciente;
import com.erppsicologo.erppsicologo.entities.Psicologo;

public class PacienteDTO {
    
    private Integer id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private Psicologo psicologo;
    private Double valor;
    private Agenda agenda;
    private String celular;
    private String telefone;
    private Date dataNascimento;
    private String senha;
    private String email;

    public PacienteDTO(Paciente paciente){
        setId(paciente.getId());
        setNome(paciente.getNome());
        setSobrenome(paciente.getSobrenome());
        setCpf(paciente.getCpf());
        setAgenda(paciente.getAgenda());
        setTelefone(paciente.getTelefone());
        setCelular(paciente.getCelular());
        setPsicologo(paciente.getPsicologo());
        setDataNascimento(paciente.getDataNascimento());
        setValor(paciente.getValor());
        setEmail(paciente.getEmail());
    }

    public PacienteDTO() {
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

    public Psicologo getPsicologo() {
        return psicologo;
    }

    public void setPsicologo(Psicologo psicologo) {
        this.psicologo = psicologo;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

}
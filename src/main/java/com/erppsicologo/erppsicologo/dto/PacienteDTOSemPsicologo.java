package com.erppsicologo.erppsicologo.dto;

import java.util.Calendar;
import java.util.Date;

import com.erppsicologo.erppsicologo.entities.Paciente;

public class PacienteDTOSemPsicologo {

    private Integer id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private Double valor;
    private String celular;
    private String telefone;
    private Date dataNascimento;
    private String email;
    private Integer idade;

    public PacienteDTOSemPsicologo(Paciente paciente) {
        setId(paciente.getId());
        setNome(paciente.getNome());
        setSobrenome(paciente.getSobrenome());
        setCpf(paciente.getCpf());
        setTelefone(paciente.getTelefone());
        setCelular(paciente.getCelular());
        setValor(paciente.getValor());
        setEmail(paciente.getEmail());
        if (paciente.getDataNascimento() != null) {
            Calendar cal = Calendar.getInstance();
            Calendar dateNascimento = Calendar.getInstance();
            dateNascimento.setTime(paciente.getDataNascimento());
            Integer idade = cal.get(Calendar.YEAR) - dateNascimento.get(Calendar.YEAR);
            setIdade(idade);
            setDataNascimento(dateNascimento.getTime());
        }
    }

    public PacienteDTOSemPsicologo(){
        
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

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

}
package com.erppsicologo.erppsicologo.dto;

import com.erppsicologo.erppsicologo.entities.Psicologo;

public class UserAuthenticateDTO {

    private String email;
    private String nome;
    private String token;

    public UserAuthenticateDTO(String email, String nome, String token){
        this.email = email;
        this.nome = nome;
        this.token = token;
    }

    public UserAuthenticateDTO(){
    }

    public static UserAuthenticateDTO toDTO(Psicologo psicologo, String token) {
        return new UserAuthenticateDTO(psicologo.getEmail(), psicologo.getNome(), token);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
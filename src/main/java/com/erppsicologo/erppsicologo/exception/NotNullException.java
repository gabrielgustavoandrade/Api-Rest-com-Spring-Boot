package com.erppsicologo.erppsicologo.exception;

import com.erppsicologo.erppsicologo.dto.PacienteDTO;
import com.erppsicologo.erppsicologo.dto.PsicologoDTO;

public class NotNullException {

    public static void verifyNullValuePsicologo(PsicologoDTO json) throws Exception {

        try {
            if (json.getNome() == null || json.getNome().isBlank())
                throw new Exception("Nome é obrigatório");

            if (json.getSobrenome() == null || json.getSobrenome().isBlank())
                throw new Exception("Sobrenome é obrigatório");

            if (json.getEmail() == null || json.getEmail().isBlank())
                throw new Exception("E-mail é obrigatório");

            if (json.getSenha() == null || json.getSenha().isBlank())
                throw new Exception("Senha é obrigatória");

            if (json.getCpf() == null || json.getCpf().isBlank())
                throw new Exception("CPF é obrigatório");

        } catch (Exception e) {
            throw e;
        }
    }

    public static void verifyNullValuePaciente(PacienteDTO json) throws Exception {
        try {
            if (json.getNome() == null || json.getNome().isBlank())
                throw new Exception("Nome é obrigatório");

            if (json.getSobrenome() == null || json.getSobrenome().isBlank())
                throw new Exception("Sobrenome é obrigatório");

            if (json.getEmail() == null || json.getEmail().isBlank())
                throw new Exception("E-mail é obrigatório");

            if (json.getTelefone() == null || json.getTelefone().isBlank())
                throw new Exception("Telefone é obrigatório");

            if (json.getCpf() == null || json.getCpf().isBlank())
                throw new Exception("CPF é obrigatório");

        } catch (Exception e) {
            throw e;
        }
    }
}
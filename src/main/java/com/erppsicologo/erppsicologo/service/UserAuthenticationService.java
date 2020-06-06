package com.erppsicologo.erppsicologo.service;


import com.erppsicologo.erppsicologo.dto.LoginDTO;
import com.erppsicologo.erppsicologo.entities.Psicologo;
import com.erppsicologo.erppsicologo.exception.ExpiredTokenException;
import com.erppsicologo.erppsicologo.repositories.PsicologoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;

@Service
public class UserAuthenticationService {

    private final PsicologoRepository repository;
    private TokenService tokenService;

    @Autowired
    public UserAuthenticationService(PsicologoRepository repository, TokenService tokenService) {
        this.repository = repository;
        this.tokenService = tokenService;
    }

    public Psicologo authenticate(LoginDTO loginDTO) {
        Psicologo psicologo = repository.findByEmail(loginDTO.getEmail());
        if (psicologo != null && loginDTO.getSenha().equals(psicologo.getSenha())) {
            return psicologo;
        } else
            return null;
    }

    public String validate(String authorization) {
        String tokenTratado = authorization.replace("Bearer", "");
        Claims claims = tokenService.decode(tokenTratado);
        if (claims == null)
            return "forbidden";
        return claims.getSubject();
    }

    public String reFresh(String authorization, String email) {
        try {
            String tokenTratado = authorization.replace("Bearer", "");
            Claims claims = tokenService.decode(tokenTratado);
            if (claims == null)
                return tokenService.generateToken(email);
            return "";
        } catch (ExpiredTokenException et) {
            et.printStackTrace();
            throw et;
        }
    }
}
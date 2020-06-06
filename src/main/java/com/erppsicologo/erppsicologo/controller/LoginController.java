package com.erppsicologo.erppsicologo.controller;

import com.erppsicologo.erppsicologo.Interface.PsicologoService;
import com.erppsicologo.erppsicologo.dto.LoginDTO;
import com.erppsicologo.erppsicologo.dto.UserAuthenticateDTO;
import com.erppsicologo.erppsicologo.dto.UserAuthenticateFailDTO;
import com.erppsicologo.erppsicologo.entities.Psicologo;
import com.erppsicologo.erppsicologo.service.TokenService;
import com.erppsicologo.erppsicologo.service.UserAuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final UserAuthenticationService userAuthenticationService;
    private final PsicologoService psicologoService;
    private UserAuthenticateFailDTO userAuthenticateFailDTO = new UserAuthenticateFailDTO();

    @Autowired
    public LoginController(UserAuthenticationService userAuthenticationService, PsicologoService psicologoService) {
        this.userAuthenticationService = userAuthenticationService;
        this.psicologoService = psicologoService;
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginDTO json) {
        TokenService tokenService = new TokenService();
        psicologoService.addUserStandard();
        String token = "";
        if (json.getEmail().isBlank()) {
            userAuthenticateFailDTO.setMessage("Não foi possível logar");
            userAuthenticateFailDTO.setMessageServer("Digite seu e-mail");
            return new ResponseEntity<UserAuthenticateFailDTO>(userAuthenticateFailDTO, HttpStatus.UNAUTHORIZED);
        }
        if (json.getSenha().isBlank()) {
            userAuthenticateFailDTO.setMessage("Não foi possível logar");
            userAuthenticateFailDTO.setMessageServer("Digite seu sua senha");
            return new ResponseEntity<UserAuthenticateFailDTO>(userAuthenticateFailDTO, HttpStatus.UNAUTHORIZED);
        }
        Psicologo psicologo = userAuthenticationService.authenticate(json);
        if (psicologo != null) {
            token = tokenService.generateToken(psicologo.getEmail());
        } else {
            userAuthenticateFailDTO.setMessage("Não foi possível logar");
            userAuthenticateFailDTO.setMessageServer("Email e/ou senha incorretos");
            return new ResponseEntity<UserAuthenticateFailDTO>(userAuthenticateFailDTO, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<UserAuthenticateDTO>(UserAuthenticateDTO.toDTO(psicologo, token),
                HttpStatus.ACCEPTED);
    }

}
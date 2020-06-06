package com.erppsicologo.erppsicologo.controller;

import java.util.List;

import com.erppsicologo.erppsicologo.Interface.ReceitaService;
import com.erppsicologo.erppsicologo.dto.ErrorCustom;
import com.erppsicologo.erppsicologo.dto.ReceitaDTO;
import com.erppsicologo.erppsicologo.dto.ReceitaOnlyIdAgendaDTO;
import com.erppsicologo.erppsicologo.service.UserAuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receita")
public class ReceitaController {

    private final ReceitaService receitaService;
    private final UserAuthenticationService userAuthenticationService;
    private ErrorCustom errorCustom;

    @Autowired
    public ReceitaController(ReceitaService receitaService, UserAuthenticationService userAuthenticationService) {
        this.receitaService = receitaService;
        this.userAuthenticationService = userAuthenticationService;
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> cadastrar(@RequestBody ReceitaDTO json, @RequestHeader String Authorization) {
        String subject = userAuthenticationService.validate(Authorization);
        if (subject.equals("forbidden")) {
            errorCustom.setMessage("Não foi possivel adicionar a receita.");
            errorCustom.setMessageServer("Token negado.");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.FORBIDDEN);
        }
        if (!subject.isBlank()) {
            return new ResponseEntity<ReceitaDTO>(receitaService.gravar(json), HttpStatus.ACCEPTED);
        } else {
            errorCustom.setMessage("Não foi possivel adicionar a receita.");
            errorCustom.setMessageServer("Revise os dados e tente novamente");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> apagar(@PathVariable Integer id, @RequestHeader String Authorization) {
        String subject = userAuthenticationService.validate(Authorization);
        if (subject.equals("forbidden")) {
            errorCustom.setMessage("Não foi possivel deletar a receita.");
            errorCustom.setMessageServer("Token negado.");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.FORBIDDEN);
        }
        if (!subject.isBlank()) {
            receitaService.remover(id);
            return new ResponseEntity<ReceitaDTO>(HttpStatus.ACCEPTED);
        } else {
            errorCustom.setMessage("Não foi possivel deletar a receita.");
            errorCustom.setMessageServer("Revise os dados e tente novamente");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscar(@PathVariable Integer id, @RequestHeader String Authorization)
            throws Exception {
        String subject = userAuthenticationService.validate(Authorization);
        if (subject.equals("forbidden")) {
            errorCustom.setMessage("Não foi possivel buscar a receita.");
            errorCustom.setMessageServer("Token negado.");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.FORBIDDEN);
        }
        if (!subject.isBlank()) {
            return new ResponseEntity<ReceitaDTO>(receitaService.buscar(id), HttpStatus.ACCEPTED);
        } else {
            errorCustom.setMessage("Não foi possivel buscar a receita.");
            errorCustom.setMessageServer("Revise os dados e tente novamente");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> alterar(@PathVariable Integer id, @RequestBody ReceitaDTO json,
            @RequestHeader String Authorization) throws Exception {
        String subject = userAuthenticationService.validate(Authorization);
        if (subject.equals("forbidden")) {
            errorCustom.setMessage("Não foi possivel alterar a receita.");
            errorCustom.setMessageServer("Token negado.");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.FORBIDDEN);
        }
        if (!subject.isBlank()) {
            return new ResponseEntity<ReceitaDTO>(receitaService.alterar(id, json), HttpStatus.ACCEPTED);
        }else {
            errorCustom.setMessage("Não foi possivel alterar a receita.");
            errorCustom.setMessageServer("Revise os dados e tente novamente");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscarTodos(@RequestHeader String Authorization) throws Exception {
        String subject = userAuthenticationService.validate(Authorization);
        if (subject.equals("forbidden")) {
            errorCustom.setMessage("Não foi possivel buscar as receitas.");
            errorCustom.setMessageServer("Token negado.");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.FORBIDDEN);
        }
        if (!subject.isBlank()) {
            return new ResponseEntity<List<ReceitaOnlyIdAgendaDTO>>(receitaService.buscarTodos(), HttpStatus.ACCEPTED);
        }else {
            errorCustom.setMessage("Não foi possivel buscar as receitas.");
            errorCustom.setMessageServer("Revise os dados e tente novamente");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
        }
    }

}
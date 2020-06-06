package com.erppsicologo.erppsicologo.controller;

import java.util.List;

import com.erppsicologo.erppsicologo.Interface.AgendaService;
import com.erppsicologo.erppsicologo.dto.AgendaDTO;
import com.erppsicologo.erppsicologo.dto.AgendaDTOSemPsicologo;
import com.erppsicologo.erppsicologo.dto.ErrorCustom;
import com.erppsicologo.erppsicologo.dto.SucessCustom;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    private final AgendaService agendaService;
    private final UserAuthenticationService userAuthenticationService;
    private ErrorCustom errorCustom = new ErrorCustom();
    private SucessCustom sucessoCustom = new SucessCustom();

    @Autowired
    public AgendaController(AgendaService agendaService, UserAuthenticationService userAuthenticationService) {
        this.agendaService = agendaService;
        this.userAuthenticationService = userAuthenticationService;
    }

    @ResponseBody
    @RequestMapping(value = "/paciente/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> cadastrar(@PathVariable Integer id, @RequestBody AgendaDTO json,
            @RequestHeader String Authorization) {
        String subject = userAuthenticationService.validate(Authorization);
        if (subject.equals("forbidden")) {
            errorCustom.setMessage("Não foi possível adicionar a agenda.");
            errorCustom.setMessageServer("Token negado.");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.FORBIDDEN);
        }
        if (!subject.isBlank()) {
            try {
                return new ResponseEntity<AgendaDTOSemPsicologo>(agendaService.gravar(id, json, subject),
                        HttpStatus.ACCEPTED);
            } catch (Exception e) {
                errorCustom.setMessage(e.getMessage());
                errorCustom.setMessageServer("erro");
                return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
            }
        } else {
            errorCustom.setMessage("Não foi possível adicionar a genda");
            errorCustom.setMessageServer("Revise os dados e tente novamente");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> apagar(@PathVariable Integer id, @RequestHeader String Authorization) {
        String subject = userAuthenticationService.validate(Authorization);
        if (subject.equals("forbidden")) {
            errorCustom.setMessage("Não foi possível deletar a agenda.");
            errorCustom.setMessageServer("Token negado.");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.FORBIDDEN);
        }
        if (!subject.isBlank()) {
            agendaService.remover(id);
            sucessoCustom.setMessage("Agenda excluída com sucesso");
            sucessoCustom.setMessageServer("Ok!");
            return new ResponseEntity<SucessCustom>(sucessoCustom, HttpStatus.ACCEPTED);
        } else {
            errorCustom.setMessage("Não foi possível deletar a genda");
            errorCustom.setMessageServer("Revise os dados e tente novamente");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscar(@RequestHeader String Authorization) throws Exception {
        String subject = userAuthenticationService.validate(Authorization);
        if (subject.equals("forbidden")) {
            errorCustom.setMessage("Não foi possível buscar as agendas.");
            errorCustom.setMessageServer("Token negado.");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.FORBIDDEN);
        }
        if (!subject.isBlank()) {
            return new ResponseEntity<List<AgendaDTOSemPsicologo>>(agendaService.buscar(subject), HttpStatus.ACCEPTED);
        } else {
            errorCustom.setMessage("Não foi possível buscar a genda");
            errorCustom.setMessageServer("Revise os dados e tente novamente");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> alterar(@PathVariable Integer id, @RequestBody AgendaDTO json,
            @RequestHeader String Authorization) throws Exception {
        String subject = userAuthenticationService.validate(Authorization);
        if (subject.equals("forbidden")) {
            errorCustom.setMessage("Não foi possível alterar a agenda.");
            errorCustom.setMessageServer("Token negado.");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.FORBIDDEN);
        }
        if (!subject.isBlank()) {
            return new ResponseEntity<AgendaDTO>(agendaService.alterar(id, json), HttpStatus.ACCEPTED);
        } else {
            errorCustom.setMessage("Não foi possível alterar a genda");
            errorCustom.setMessageServer("Revise os dados e tente novamente");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/todos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscarTodos(@RequestHeader String Authorization) throws Exception {
        String subject = userAuthenticationService.validate(Authorization);
        if (subject.equals("forbidden")) {
            errorCustom.setMessage("Não foi possível buscar as agendas.");
            errorCustom.setMessageServer("Token negado.");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.FORBIDDEN);
        }
        if (!subject.isBlank()) {
            return new ResponseEntity<List<AgendaDTO>>(agendaService.buscarTodos(), HttpStatus.ACCEPTED);
        } else {
            errorCustom.setMessage("Não foi possível buscar as agendas");
            errorCustom.setMessageServer("Revise os dados e tente novamente");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/paciente/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscarAgendaPacientesByPsicologo(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
            @PathVariable Integer id, @RequestHeader String Authorization) throws Exception {
        String subject = userAuthenticationService.validate(Authorization);
        if (subject.equals("forbidden")) {
            errorCustom.setMessage("Não foi possível buscar as agendas.");
            errorCustom.setMessageServer("Token negado.");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.FORBIDDEN);
        }
        if (!subject.isBlank()) {
            return new ResponseEntity<List<AgendaDTOSemPsicologo>>(
                    agendaService.buscarAgendaPacientesByPsicologo(id, subject, limit, page), HttpStatus.ACCEPTED);
        } else {
            errorCustom.setMessage("Não foi possível buscar as agendas");
            errorCustom.setMessageServer("Revise os dados e tente novamente");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
        }
    }

}
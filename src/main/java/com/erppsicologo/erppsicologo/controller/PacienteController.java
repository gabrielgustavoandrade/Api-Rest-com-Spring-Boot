package com.erppsicologo.erppsicologo.controller;

import java.util.List;

import com.erppsicologo.erppsicologo.Interface.PacienteService;
import com.erppsicologo.erppsicologo.dto.ErrorCustom;
import com.erppsicologo.erppsicologo.dto.PacienteDTO;
import com.erppsicologo.erppsicologo.dto.PacienteDTOSemPsicologo;
import com.erppsicologo.erppsicologo.dto.SucessCustom;
import com.erppsicologo.erppsicologo.exception.NotNullException;
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
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;
    private final UserAuthenticationService userAuthenticationService;
    private ErrorCustom errorCustom = new ErrorCustom();
    private SucessCustom sucessoCustom = new SucessCustom();

    @Autowired
    public PacienteController(PacienteService pacienteService, UserAuthenticationService userAuthenticationService) {
        this.pacienteService = pacienteService;
        this.userAuthenticationService = userAuthenticationService;
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> cadastrar(@RequestBody PacienteDTO json, @RequestHeader String Authorization) {
        String subject = userAuthenticationService.validate(Authorization);
        if (subject.equals("forbidden")) {
            errorCustom.setMessage("Não foi possivel adicionar o paciente.");
            errorCustom.setMessageServer("Token negado.");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.FORBIDDEN);
        }
        if (!subject.isBlank()) {
            try {
                NotNullException.verifyNullValuePaciente(json);
            } catch (Exception e) {
                errorCustom.setMessage(e.getMessage());
                errorCustom.setMessageServer("erro");
                return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
            }
            PacienteDTOSemPsicologo pacienteDTO = pacienteService.findByEmail(json.getEmail());
            if (pacienteDTO.getEmail() != null && !pacienteDTO.getEmail().isEmpty()) {
                errorCustom.setMessage("Não foi possivel adicionar o paciente");
                errorCustom.setMessageServer("Já existe um paciente com esse e-mail.");
                return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<PacienteDTOSemPsicologo>(pacienteService.gravar(json, subject),
                        HttpStatus.ACCEPTED);
            }
        } else {
            errorCustom.setMessage("Não foi possivel adicionar o paciente");
            errorCustom.setMessageServer("Revise os dados e tente novamente");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> apagar(@PathVariable Integer id, @RequestHeader String Authorization) {
        String subject = userAuthenticationService.validate(Authorization);
        if (subject.equals("forbidden")) {
            errorCustom.setMessage("Não foi possivel excluir o paciente.");
            errorCustom.setMessageServer("Token negado.");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.FORBIDDEN);
        }
        if (!subject.isBlank()) {
            Boolean ex = pacienteService.remover(id);
            if (ex) {
                sucessoCustom.setMessage("Paciente excluído com sucesso");
                sucessoCustom.setMessageServer("Ok!");
                return new ResponseEntity<SucessCustom>(sucessoCustom, HttpStatus.ACCEPTED);
            } else {
                errorCustom.setMessage("Psicologo inexistente");
                errorCustom.setMessageServer("Erro");
                return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.FAILED_DEPENDENCY);
            }
        } else {
            errorCustom.setMessage("Não foi possivel excluir o paciente");
            errorCustom.setMessageServer("Revise os dados e tente novamente");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscar(@PathVariable Integer id, @RequestHeader String Authorization) throws Exception {
        String subject = userAuthenticationService.validate(Authorization);
        if (subject.equals("forbidden")) {
            errorCustom.setMessage("Não foi possivel buscar o paciente.");
            errorCustom.setMessageServer("Token negado.");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.FORBIDDEN);
        }
        if (!subject.isBlank()) {
            return new ResponseEntity<PacienteDTOSemPsicologo>(pacienteService.buscar(id), HttpStatus.ACCEPTED);
        } else {
            errorCustom.setMessage("Não foi possivel buscar o paciente.");
            errorCustom.setMessageServer("Revise os dados e tente novamente");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
        }

    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> alterar(@PathVariable Integer id, @RequestBody PacienteDTO json,
            @RequestHeader String Authorization) throws Exception {
        String subject = userAuthenticationService.validate(Authorization);
        if (subject.equals("forbidden")) {
            errorCustom.setMessage("Não foi possivel adicionar o paciente.");
            errorCustom.setMessageServer("Token negado.");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.FORBIDDEN);
        }
        if (!subject.isBlank()) {
            try {
                NotNullException.verifyNullValuePaciente(json);
            } catch (Exception e) {
                errorCustom.setMessage(e.getMessage());
                errorCustom.setMessageServer("erro");
                return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
            }
            PacienteDTOSemPsicologo pacienteDTO = pacienteService.findByEmail(json.getEmail());
            if (pacienteDTO.getEmail() != null && !pacienteDTO.getEmail().isEmpty()) {
                errorCustom.setMessage("Não foi possivel alterar o paciente");
                errorCustom.setMessageServer("Já existe um paciente com esse e-mail.");
                return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<PacienteDTOSemPsicologo>(pacienteService.alterar(id, json, subject),
                        HttpStatus.ACCEPTED);
            }
        } else {
            errorCustom.setMessage("Não foi possivel alterar o paciente.");
            errorCustom.setMessageServer("Revise os dados e tente novamente");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllByPsicolgoEmail(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit, @RequestHeader String Authorization)
            throws Exception {
        String subject = userAuthenticationService.validate(Authorization);
        if (subject.equals("forbidden")) {
            errorCustom.setMessage("Não foi possivel buscar o paciente.");
            errorCustom.setMessageServer("Token negado.");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.FORBIDDEN);
        }
        if (!subject.isBlank()) {
            
            return new ResponseEntity<List<PacienteDTOSemPsicologo>>(pacienteService.findAllByPsicolgoEmail(subject, limit, page),
                    HttpStatus.ACCEPTED);
        } else {
            errorCustom.setMessage("Não foi possivel buscar o paciente.");
            errorCustom.setMessageServer("Revise os dados e tente novamente");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/todos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscarTodos(@RequestHeader String Authorization) throws Exception {
        String subject = userAuthenticationService.validate(Authorization);
        if (subject.equals("forbidden")) {
            errorCustom.setMessage("Não foi possivel buscar o paciente.");
            errorCustom.setMessageServer("Token negado.");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.FORBIDDEN);
        }
        if (!subject.isBlank()) {
            return new ResponseEntity<List<PacienteDTOSemPsicologo>>(pacienteService.buscarTodos(),
                    HttpStatus.ACCEPTED);
        } else {
            errorCustom.setMessage("Não foi possivel buscar o paciente.");
            errorCustom.setMessageServer("Revise os dados e tente novamente");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
        }
    }

}
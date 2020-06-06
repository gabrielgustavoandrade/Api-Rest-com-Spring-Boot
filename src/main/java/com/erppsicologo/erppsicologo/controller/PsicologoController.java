package com.erppsicologo.erppsicologo.controller;

import java.util.List;

import com.erppsicologo.erppsicologo.Interface.PsicologoService;
import com.erppsicologo.erppsicologo.dto.ErrorCustom;
import com.erppsicologo.erppsicologo.dto.PsicologoDTO;
import com.erppsicologo.erppsicologo.dto.PsicologoDTOSemPaciente;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/psicologo")
public class PsicologoController {

    private final UserAuthenticationService userAuthenticationService;
    private final PsicologoService psicologoService;
    private ErrorCustom errorCustom = new ErrorCustom();
    private SucessCustom sucessoCustom = new SucessCustom();

    @Autowired
    public PsicologoController(PsicologoService psicologoService, UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
        this.psicologoService = psicologoService;
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> cadastrar(@RequestBody PsicologoDTO json, @RequestHeader String Authorization) {
        String subject = userAuthenticationService.validate(Authorization);
        if (subject.equals("admin@admin.com")) {
            if (subject.equals("forbidden")) {
                errorCustom.setMessage("Não foi possivel adicionar o psicologo.");
                errorCustom.setMessageServer("Token negado.");
                return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.FORBIDDEN);
            }
            if (!subject.isBlank()) {
                try {
                    NotNullException.verifyNullValuePsicologo(json);
                } catch (Exception e) {
                    errorCustom.setMessage(e.getMessage());
                    errorCustom.setMessageServer("erro");
                    return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
                }
                PsicologoDTO psicologoDTO = new PsicologoDTO();
                psicologoDTO = psicologoService.findByEmail(json.getEmail());
                if (psicologoDTO.getEmail() != null) {
                    errorCustom.setMessage("Não foi possivel adicionar o psicologo");
                    errorCustom.setMessageServer("Já existe um psicologo com esse e-mail.");
                    return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.CONFLICT);
                } else {
                    psicologoDTO = psicologoService.gravar(json);
                    return new ResponseEntity<PsicologoDTO>(psicologoDTO, HttpStatus.ACCEPTED);
                }
            } else {
                errorCustom.setMessage("Não foi possivel adicionar o psicologo");
                errorCustom.setMessageServer("Revise os dados e tente novamente");
                return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
            }
        } else {
            errorCustom.setMessage("Não foi possivel buscar o psicologo.");
            errorCustom.setMessageServer("Você não tem permissão para isso.");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.UNAUTHORIZED);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> apagar(@PathVariable Integer id, @RequestHeader String Authorization) throws Exception {
        String subject = userAuthenticationService.validate(Authorization);
        if (subject.equals("admin@admin.com")) {
            if (subject.equals("forbidden")) {
                errorCustom.setMessage("Não foi possivel excluir o psicologo.");
                errorCustom.setMessageServer("Token negado.");
                return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.FORBIDDEN);
            }
            if (!subject.isBlank()) {
                Boolean ex = psicologoService.remover(id);
                if (ex) {
                    sucessoCustom.setMessage("Psicologo excluído com sucesso");
                    sucessoCustom.setMessageServer("Ok!");
                    return new ResponseEntity<SucessCustom>(sucessoCustom, HttpStatus.ACCEPTED);
                } else {
                    errorCustom.setMessage("Psicologo inexistente");
                    errorCustom.setMessageServer("Erro");
                    return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.FAILED_DEPENDENCY);
                }
            } else {
                errorCustom.setMessage("Não foi possivel excluir o psicologo.");
                errorCustom.setMessageServer("Revise os dados e tente novamente");
                return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
            }
        } else {
            errorCustom.setMessage("Não foi possivel buscar o psicologo.");
            errorCustom.setMessageServer("Você não tem permissão para isso.");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.UNAUTHORIZED);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscar(@PathVariable Integer id, @RequestHeader String Authorization) throws Exception {
        String subject = userAuthenticationService.validate(Authorization);
        if (subject.equals("admin@admin.com")) {
            if (subject.equals("forbidden")) {
                errorCustom.setMessage("Não foi possivel buscar o psicologo.");
                errorCustom.setMessageServer("Token negado.");
                return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.FORBIDDEN);
            }
            if (!subject.isBlank()) {
                return new ResponseEntity<PsicologoDTO>(psicologoService.buscar(id), HttpStatus.ACCEPTED);
            } else {
                errorCustom.setMessage("Não foi possivel buscar o psicologo.");
                errorCustom.setMessageServer("Revise os dados e tente novamente");
                return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
            }
        } else {
            errorCustom.setMessage("Não foi possivel buscar o psicologo.");
            errorCustom.setMessageServer("Você não tem permissão para isso.");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.UNAUTHORIZED);
        }
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> alterar(@RequestBody PsicologoDTO json, @RequestHeader String Authorization)
            throws Exception {
        String subject = userAuthenticationService.validate(Authorization);
        if (subject.equals("forbidden")) {
            errorCustom.setMessage("Não foi possivel buscar o psicologo.");
            errorCustom.setMessageServer("Token negado.");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.FORBIDDEN);
        }
        if (!subject.isBlank()) {
            try {
                NotNullException.verifyNullValuePsicologo(json);
            } catch (Exception e) {
                errorCustom.setMessage(e.getMessage());
                errorCustom.setMessageServer("erro");
                return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
            }
            PsicologoDTOSemPaciente psicologoDTOSemPaciente = psicologoService.alterar(json.getEmail(), json);
            return new ResponseEntity<PsicologoDTOSemPaciente>(psicologoDTOSemPaciente, HttpStatus.ACCEPTED);
        } else {
            errorCustom.setMessage("Não foi possivel alterar o psicologo.");
            errorCustom.setMessageServer("Revise os dados e tente novamente");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscarTodos(@RequestHeader String Authorization) throws Exception {
        String subject = userAuthenticationService.validate(Authorization);
        if (subject.equals("admin@admin.com")) {
            if (subject.equals("forbidden")) {
                errorCustom.setMessage("Não foi possivel buscar o psicologo.");
                errorCustom.setMessageServer("Token negado.");
                return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.FORBIDDEN);
            }
            if (!subject.isBlank()) {
                return new ResponseEntity<List<PsicologoDTOSemPaciente>>(psicologoService.buscarTodos(), HttpStatus.ACCEPTED);
            } else {
                errorCustom.setMessage("Não foi possivel buscar o psicologo.");
                errorCustom.setMessageServer("Revise os dados e tente novamente");
                return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
            }
        } else {
            errorCustom.setMessage("Não foi possivel buscar o psicologo.");
            errorCustom.setMessageServer("Você não tem permissão para isso.");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.UNAUTHORIZED);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/logado", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByLogged(@RequestHeader String Authorization) throws Exception {
        String subject = userAuthenticationService.validate(Authorization);
        if (subject.equals("forbidden")) {
            errorCustom.setMessage("Não foi possivel buscar o psicologo.");
            errorCustom.setMessageServer("Token negado.");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.FORBIDDEN);
        }
        if (!subject.isBlank()) {
            PsicologoDTO psicologoDTO = psicologoService.findLogged(subject);
            return new ResponseEntity<PsicologoDTO>(psicologoDTO, HttpStatus.ACCEPTED);
        } else {
            errorCustom.setMessage("Não foi possivel buscar o psicologo.");
            errorCustom.setMessageServer("Revise os dados e tente novamente");
            return new ResponseEntity<ErrorCustom>(errorCustom, HttpStatus.BAD_REQUEST);
        }
    }

}
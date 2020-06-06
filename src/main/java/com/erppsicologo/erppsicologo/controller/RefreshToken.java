package com.erppsicologo.erppsicologo.controller;

import com.erppsicologo.erppsicologo.service.UserAuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefreshToken {

    private final UserAuthenticationService userAuthenticationService;

    @Autowired
    public RefreshToken(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    @ResponseBody
    @RequestMapping(value = "/refreshtoken/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestHeader String authorization, @PathVariable String email) {
        return userAuthenticationService.reFresh(authorization, email);
    }

}
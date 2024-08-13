package com.ENAA_SUPPORT.controller;

import com.ENAA_SUPPORT.config.JwtAuth;
import com.ENAA_SUPPORT.dto.JwtDto;
import com.ENAA_SUPPORT.model.Person;
import com.ENAA_SUPPORT.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private PersonService personService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public JwtDto login(@RequestBody Person personLogin) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(personLogin.getUsername(), personLogin.getPassword())
        );
        Person person = personService.findByUsername(personLogin.getUsername());
        Set<String> roles = person.getRoles().stream()
                .map(role -> role.name())
                .collect(Collectors.toSet());
        Integer userId = person.getId();
        String token = JwtAuth.generateToken(personLogin.getUsername(), roles);
        return new JwtDto(userId , token);
    }


}

package com.ENAA_SUPPORT.Controller;

import com.ENAA_SUPPORT.Config.JwtAuth;
import com.ENAA_SUPPORT.Dto.JwtDto;
import com.ENAA_SUPPORT.Model.Person;
import com.ENAA_SUPPORT.Service.PersonService;
import com.ENAA_SUPPORT.Service.UserService;
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

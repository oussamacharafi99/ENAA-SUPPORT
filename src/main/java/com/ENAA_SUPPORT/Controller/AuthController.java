package com.ENAA_SUPPORT.Controller;

import com.ENAA_SUPPORT.Config.JwtAuth;
import com.ENAA_SUPPORT.Dto.JwtDto;
import com.ENAA_SUPPORT.Dto.PersonDto;
import com.ENAA_SUPPORT.Enum.Role;
import com.ENAA_SUPPORT.Model.Person;
import com.ENAA_SUPPORT.Model.Technician;
import com.ENAA_SUPPORT.Model.User;
import com.ENAA_SUPPORT.Repository.PersonRepo;
import com.ENAA_SUPPORT.Repository.TechnicianRepo;
import com.ENAA_SUPPORT.Service.PersonService;
import com.ENAA_SUPPORT.Service.TechicianService;
import com.ENAA_SUPPORT.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private PersonService personService;

    @Autowired
    private TechicianService techicianService;

    @Autowired
    private UserService userService;


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

    @PostMapping("/user")
    public String saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return "the user is saved";
    }

    @PostMapping("/technician")
    public String saveTechnician(@RequestBody Technician technician) {
        techicianService.saveTechnician(technician);
        return "the technician is saved";
    }

    @GetMapping("get_Person")
    public List<Person> getAll(){
        return personService.findAll();
    }

    @GetMapping("get_persons_dto")
    public List<PersonDto> getAllDto(){
        return personService.getPersons();
    }

    @GetMapping("get_technicians")
    public List<PersonDto> getTechnicians(){
        return personService.getTechnicians();
    }

}

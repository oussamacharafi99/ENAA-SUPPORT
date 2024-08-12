package com.ENAA_SUPPORT.Controller;

import com.ENAA_SUPPORT.Dto.PersonDto;
import com.ENAA_SUPPORT.Model.Person;
import com.ENAA_SUPPORT.Model.Technician;
import com.ENAA_SUPPORT.Model.User;
import com.ENAA_SUPPORT.Service.PersonService;
import com.ENAA_SUPPORT.Service.TechicianService;
import com.ENAA_SUPPORT.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/person")
public class PersonController {

    @Autowired
    private TechicianService techicianService;

    @Autowired
    private UserService userService;

    @Autowired
    private PersonService personService;

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

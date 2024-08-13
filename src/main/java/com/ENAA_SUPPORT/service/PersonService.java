package com.ENAA_SUPPORT.service;

import com.ENAA_SUPPORT.dto.PersonDto;
import com.ENAA_SUPPORT.enums.Role;
import com.ENAA_SUPPORT.model.Person;
import com.ENAA_SUPPORT.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonService {
    @Autowired
    private PersonRepo personRepo;

    public Person findByUsername(String username) {
        return personRepo.findByUsername(username);
    }

    public List<Person> findAll() {
//        return personRepo.findAll().stream()
//                .filter(person -> person.getRoles().contains(Role.ROLE_USER) || person.getRoles().contains(Role.ROLE_TECHNICIAN))
//                .collect(Collectors.toList());

        List<Role> roles = new ArrayList<>();
        roles.add(Role.ROLE_USER);
        roles.add(Role.ROLE_TECHNICIAN);
        return personRepo.findByRolesIn(roles);
    }

    public List<PersonDto> getPersons() {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);
        return personRepo.findAll().stream()
                .filter(person -> person.getRoles().equals(roles))
                .map(person -> {
                    PersonDto personDto = new PersonDto();
                    personDto.setId(person.getId());
                    personDto.setUsername(person.getUsername());
                    return personDto;
                })
                .collect(Collectors.toList());
    }

    public List<PersonDto> getTechnicians() {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_TECHNICIAN);
        return personRepo.findAll().stream()
                .filter(person -> person.getRoles().equals(roles))
                .map(person -> {
                    PersonDto personDto = new PersonDto();
                    personDto.setId(person.getId());
                    personDto.setUsername(person.getUsername());
                    return personDto;
                })
                .collect(Collectors.toList());
    }
}

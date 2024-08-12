package com.ENAA_SUPPORT.Service;

import com.ENAA_SUPPORT.Enum.Role;
import com.ENAA_SUPPORT.Model.Person;
import com.ENAA_SUPPORT.Model.Technician;
import com.ENAA_SUPPORT.Model.User;
import com.ENAA_SUPPORT.Repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
}

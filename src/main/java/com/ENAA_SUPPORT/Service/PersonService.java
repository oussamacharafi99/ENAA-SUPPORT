package com.ENAA_SUPPORT.Service;

import com.ENAA_SUPPORT.Enum.Role;
import com.ENAA_SUPPORT.Model.Person;
import com.ENAA_SUPPORT.Model.Technician;
import com.ENAA_SUPPORT.Model.User;
import com.ENAA_SUPPORT.Repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PersonService {
    @Autowired
    private PersonRepo personRepo;

    public Person findByUsername(String username) {
        return personRepo.findByUsername(username);
    }



}

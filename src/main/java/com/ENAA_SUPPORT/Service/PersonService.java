package com.ENAA_SUPPORT.Service;

import com.ENAA_SUPPORT.Model.Person;
import com.ENAA_SUPPORT.Repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private PersonRepo personRepo;

    public Person findByUsername(String username) {
        return personRepo.findByUsername(username);
    }
}

package com.ENAA_SUPPORT.service;
import com.ENAA_SUPPORT.enums.Role;
import com.ENAA_SUPPORT.model.User;
import com.ENAA_SUPPORT.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);
        user.setRoles(roles);
        return userRepo.save(user);
    }
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}

package com.ENAA_SUPPORT.Service;

import com.ENAA_SUPPORT.Enum.Role;
import com.ENAA_SUPPORT.Model.Technician;
import com.ENAA_SUPPORT.Repository.TechnicianRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TechicianService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private TechnicianRepo technicianRepo;

    public Technician saveTechnician(Technician technician) {
        technician.setPassword(passwordEncoder.encode(technician.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_TECHNICIAN);
        technician.setRoles(roles);
        return technicianRepo.save(technician);
    }
}

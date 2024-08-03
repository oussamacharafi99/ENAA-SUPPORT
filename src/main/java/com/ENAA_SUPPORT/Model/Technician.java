package com.ENAA_SUPPORT.Model;

import com.ENAA_SUPPORT.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Technician extends Person {
    @Override
    public Set<Role> getRoles() {
        setRoles(Collections.singleton(Role.ROLE_TECHNICIAN));
        return super.getRoles();
    }
}

package com.ENAA_SUPPORT.Repository;
import com.ENAA_SUPPORT.Enum.Role;
import com.ENAA_SUPPORT.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {
    Person findByUsername(String username);
    List<Person> findByRolesIn(List<Role> roles);
}

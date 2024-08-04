package com.ENAA_SUPPORT.Repository;
import com.ENAA_SUPPORT.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {
    Person findByUsername(String username);
}

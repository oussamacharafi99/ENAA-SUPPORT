package com.ENAA_SUPPORT.Repository;

import com.ENAA_SUPPORT.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
}

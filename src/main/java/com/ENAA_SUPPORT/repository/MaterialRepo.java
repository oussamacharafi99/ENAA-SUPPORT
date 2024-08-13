package com.ENAA_SUPPORT.repository;

import com.ENAA_SUPPORT.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepo extends JpaRepository<Material, Integer> {
    long count();
}

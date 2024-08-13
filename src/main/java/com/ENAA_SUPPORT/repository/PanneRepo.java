package com.ENAA_SUPPORT.repository;

import com.ENAA_SUPPORT.model.Panne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanneRepo extends JpaRepository<Panne, Integer> {
}

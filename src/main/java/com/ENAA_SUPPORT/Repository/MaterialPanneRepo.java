package com.ENAA_SUPPORT.Repository;

import com.ENAA_SUPPORT.Model.MaterialPanne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialPanneRepo extends JpaRepository<MaterialPanne, Integer> {
}

package com.ENAA_SUPPORT.Repository;

import com.ENAA_SUPPORT.Model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepo extends JpaRepository<Material, Integer> {
}

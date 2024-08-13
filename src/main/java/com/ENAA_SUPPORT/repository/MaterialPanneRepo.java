package com.ENAA_SUPPORT.repository;

import com.ENAA_SUPPORT.model.MaterialPanne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialPanneRepo extends JpaRepository<MaterialPanne, Integer> {
    @Query("SELECT COALESCE(MAX(mp.id.historiesId), 0) FROM MaterialPanne mp")
    Integer findMaxHistoryId();

    @Query(value = "SELECT * FROM material_panne WHERE material_id=? AND panne_id=? AND end_date is null ",nativeQuery = true)
    MaterialPanne findByCompositeId(Integer mid,Integer pid);
}

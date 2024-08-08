package com.ENAA_SUPPORT.Repository;

import com.ENAA_SUPPORT.Model.MaterialPanne;
import com.ENAA_SUPPORT.Model.MaterialPanneId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialPanneRepo extends JpaRepository<MaterialPanne, Integer> {
    @Query("SELECT COALESCE(MAX(mp.id.historiesId), 0) FROM MaterialPanne mp")
    Integer findMaxHistoryId();

    MaterialPanne findByMaterialId(Integer materialId);
}

package com.ENAA_SUPPORT.Repository;
import com.ENAA_SUPPORT.Model.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicianRepo extends JpaRepository<Technician , Integer> {
}

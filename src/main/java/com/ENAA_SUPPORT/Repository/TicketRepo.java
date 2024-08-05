package com.ENAA_SUPPORT.Repository;

import com.ENAA_SUPPORT.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Integer> {
    List<Ticket> findTicketsByTechnicianId(int technicianId);
}

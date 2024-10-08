package com.ENAA_SUPPORT.repository;

import com.ENAA_SUPPORT.enums.TicketStatus;
import com.ENAA_SUPPORT.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Integer> {
    List<Ticket> findTicketsByTechnicianId(int technicianId);

//    @Query(value = "SELECT t.description , t.date_creation, t.status, t.technical_description, m.name , p.type FROM ticket t INNER JOIN material m ON m.id = t.material_id INNER JOIN panne p ON p.id = t.panne_id" , nativeQuery = true)
//    List<TicketDto> findAllTickets();

//    @Query(value = "SELECT t.description , t.date_creation, t.status, t.technical_description, m.name , p.type FROM ticket t INNER JOIN material m ON m.id = t.material_id INNER JOIN panne p ON p.id = t.panne_id WHERE t.user_id = ?" , nativeQuery = true)
//    List<TicketDto> getAllUserTickets(Integer id);
//
//    @Query(value = "SELECT  t.description , t.date_creation, t.status, t.technical_description, m.name , p.type FROM ticket t INNER JOIN material m ON m.id = t.material_id INNER JOIN panne p ON p.id = t.panne_id WHERE t.technician_id = ?" , nativeQuery = true)
//    List<TicketDto> getAllTechnicianTickets(Integer id);

    List<Ticket> findAllByUserId(int userId);
    List<Ticket> findTicketsByStatus(TicketStatus status);
    List<Ticket> findTicketsByTechnicianIdAndStatus(Integer technician_id, TicketStatus status);
}

package com.ENAA_SUPPORT.Service;
import com.ENAA_SUPPORT.Enum.TicketStatus;
import com.ENAA_SUPPORT.Model.Panne;
import com.ENAA_SUPPORT.Model.Ticket;
import com.ENAA_SUPPORT.Repository.PanneRepo;
import com.ENAA_SUPPORT.Repository.TicketRepo;
import com.ENAA_SUPPORT.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepo ticketRepo;

    @Autowired
    private PanneRepo panneRepo;

    public List<Ticket> getAllTickets() {
        return ticketRepo.findAll();
    }

    public Ticket getTicketById(int id) {
        return ticketRepo.findById(id).get();
    }

    public void addTicket(Ticket ticket) {
        Panne panne = new Panne();
        panne.setMaterial(ticket.getMaterial());
        panne.setDate(LocalDate.now());
        panne.setDescription(ticket.getDescription());
        panneRepo.save(panne);
//      ---** for ticket **--  //
        ticket.setDateCreation(LocalDate.now());
        ticket.setTechnician(null);
        ticket.setStatus(TicketStatus.FAILURE);
        ticket.setTechnicalDescription(null);
        ticketRepo.save(ticket);
    }

    public Ticket updateTicketByTechnicien(Ticket ticket , Integer id) {
        Ticket ticket1 = ticketRepo.findById(id).orElseThrow();
        ticket1.setStatus(TicketStatus.FIXED);
        ticket1.setTechnicalDescription(ticket.getTechnicalDescription());
        return ticketRepo.save(ticket1);
    }
    public Ticket updateTicketByAdmin(Ticket ticket , Integer id) {
        Ticket ticket1 = ticketRepo.findById(id).orElseThrow();
        ticket1.setTechnician(ticket.getTechnician());
        ticket1.setStatus(TicketStatus.PROCESSING);
        return ticketRepo.save(ticket1);
    }

}

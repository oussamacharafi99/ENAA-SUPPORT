package com.ENAA_SUPPORT.Controller;

import com.ENAA_SUPPORT.Dto.TicketDto;
import com.ENAA_SUPPORT.Model.Ticket;
import com.ENAA_SUPPORT.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("add")
    public String addTicket(@RequestBody Ticket ticket) {
        ticketService.addTicket(ticket);
        return "the ticket is added successfully";
    }

    @GetMapping("getall")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("get_by_id/{id}")
    public Ticket getTicketById(@PathVariable int id) {
        return ticketService.getTicketById(id);
    }

    @PutMapping("/update_by_technician/{id}")
    public String updateTicketByTechnician(@PathVariable Integer id, @RequestBody Ticket ticket) {
        ticketService.updateTicketByTechnicien(ticket , id);
        return "the ticket is updated successfully from technician !";
    }

    @PutMapping("/update_by_admin/{id}")
    public String updateTicketByAdmin(@PathVariable Integer id, @RequestBody Ticket ticket) {
        ticketService.updateTicketByAdmin(ticket , id);
        return "the ticket is updated successfully from admin !";
    }

    @GetMapping("get_technician_tickets/{id}")
    public List<TicketDto> getAllTicketsByTechnician(@PathVariable Integer id) {
        return ticketService.getTicketsByTechnicienId(id);
    }

}

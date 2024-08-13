package com.ENAA_SUPPORT.controller;

import com.ENAA_SUPPORT.dto.TicketDto;
import com.ENAA_SUPPORT.dto.TicketsTechnicalDescriptionDto;
import com.ENAA_SUPPORT.dto.TicketsTechnicianIdDto;
import com.ENAA_SUPPORT.model.Ticket;
import com.ENAA_SUPPORT.service.TicketService;
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

    @GetMapping("get_by_id/{id}")
    public Ticket getTicketById(@PathVariable int id) {
        return ticketService.getTicketById(id);
    }

    @PutMapping("update_by_technician/{id}")
    public String updateTicketByTechnician(@PathVariable Integer id, @RequestBody TicketsTechnicalDescriptionDto ticket) {
        ticketService.updateTicketByTechnicien(ticket , id);
        return "the ticket is updated successfully from technician !";
    }

    @PutMapping("update_by_admin/{id}")
    public String updateTicketByAdmin(@PathVariable Integer id, @RequestBody TicketsTechnicianIdDto ticket) {
        ticketService.updateTicketByAdmin(ticket , id);
        return "the ticket is updated successfully from admin !";
    }

    @GetMapping("get_technician_tickets/{id}")
    public List<TicketDto> getAllTicketsByTechnician(@PathVariable Integer id) {
        return ticketService.getTicketsByTechnicienId(id);
    }

    @GetMapping("get_user_tickets/{id}")
    public List<TicketDto> getAllTicketsByUser(@PathVariable Integer id) {
        return ticketService.getTicketsByUserId(id);
    }

    @GetMapping("get_tickets_failure")
    public List<TicketDto> TicketStatusFailure() {
        return ticketService.TicketStatusFailure();
    }

    @GetMapping("get_tickets_fixed")
    public List<TicketDto> TicketStatusFixed() {
        return ticketService.TicketStatusFixed();
    }

    @GetMapping("get_tickets_processing")
    public List<TicketDto> TicketStatusProcessing() {
        return ticketService.TicketStatusProcessing();
    }


    @GetMapping("get_tickets_processing/{id}")
    public List<TicketDto> TicketStatusProcessing(@PathVariable Integer id) {
        return ticketService.TicketByTechnicianIdAndStatusProcessing(id);
    }
    @GetMapping("get_tickets_fixed/{id}")
    public List<TicketDto> TicketStatusFixed(@PathVariable Integer id) {
        return ticketService.TicketByTechnicianIdAndStatusFixed(id);
    }


}

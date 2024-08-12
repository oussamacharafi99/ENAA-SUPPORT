package com.ENAA_SUPPORT.Service;
import com.ENAA_SUPPORT.Dto.TicketDto;
import com.ENAA_SUPPORT.Enum.MaterialEtat;
import com.ENAA_SUPPORT.Enum.TicketStatus;
import com.ENAA_SUPPORT.Model.*;
import com.ENAA_SUPPORT.Repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private static final Logger log = LoggerFactory.getLogger(TicketService.class);
    @Autowired
    private TicketRepo ticketRepo;

    @Autowired
    private PanneRepo panneRepo;

    @Autowired
    private MaterialRepo materialRepo;

    @Autowired
    private MaterialPanneRepo materialPanneRepo;

    public List<Ticket> getAllTickets() {
        return ticketRepo.findAll();
    }

    public Ticket getTicketById(int id) {
        return ticketRepo.findById(id).get();
    }

    public void addTicket(Ticket ticket) {
        /*----for update material state------*/
        Material material = materialRepo.findById(ticket.getMaterial().getId()).orElseThrow(() -> new RuntimeException("Material not found"));
        Panne panne = panneRepo.findById(ticket.getPanne().getId()).orElseThrow(() -> new RuntimeException("Panne not found"));
        material.setEtat(MaterialEtat.OUT_SERVICE);
        materialRepo.save(material);

        /*----for add historic------*/
        Integer histroriesId = materialPanneRepo.findMaxHistoryId() + 1;
        MaterialPanneId materialPanneId = new MaterialPanneId(ticket.getMaterial().getId(), ticket.getPanne().getId(), histroriesId);
        MaterialPanne materialPanne = new MaterialPanne();
        materialPanne.setId(materialPanneId);
        materialPanne.setMaterial(material);
        materialPanne.setPanne(panne);
        materialPanne.setDescription(ticket.getDescription());
        materialPanne.setStartDate(LocalDate.now());
        materialPanneRepo.save(materialPanne);

        /*----for add ticket------*/
        ticket.setDateCreation(LocalDate.now());
        ticket.setTechnician(null);
        ticket.setStatus(TicketStatus.FAILURE);
        ticket.setTechnicalDescription(null);
        ticketRepo.save(ticket);
    }


    public Ticket updateTicketByAdmin(Ticket ticket , Integer id) {
        Ticket ticket1 = ticketRepo.findById(id).orElseThrow();
        ticket1.setTechnician(ticket.getTechnician());
        ticket1.setStatus(TicketStatus.PROCESSING);
        return ticketRepo.save(ticket1);
    }

    public Ticket updateTicketByTechnicien(Ticket ticket , Integer id) {
        Ticket ticket1 = ticketRepo.findById(id).orElseThrow();
        ticket1.setStatus(TicketStatus.FIXED);
        ticket1.setTechnicalDescription(ticket.getTechnicalDescription());

        MaterialPanne materialPanne = materialPanneRepo.findByMaterialId(ticket1.getMaterial().getId());
        System.out.println("===----000--== : " + materialPanne.getStartDate());
        materialPanne.setEndDate(LocalDate.now());
        materialPanneRepo.save(materialPanne);


        Material material = materialRepo.findById(ticket1.getMaterial().getId()).orElseThrow();
        material.setEtat(MaterialEtat.REPAIR);
        materialRepo.save(material);

        return ticketRepo.save(ticket1);
    }

    public List<TicketDto> getTicketsByTechnicienId(Integer id) {

        List<Ticket> tickets = ticketRepo.findTicketsByTechnicianId(id);

        List<TicketDto> ticketDtos = tickets.stream().map(ticket -> {
            TicketDto dto = new TicketDto();
            dto.setDescription(ticket.getDescription());
            dto.setDateCreation(ticket.getDateCreation());
            dto.setStatus(ticket.getStatus());
            dto.setTechnicalName(ticket.getTechnician().getUsername());
            dto.setTechnicalDescription(ticket.getTechnicalDescription());
            dto.setMaterialName(ticket.getMaterial().getName());
            dto.setPanneType(ticket.getPanne().getType());
            return dto;
        }).collect(Collectors.toList());

        return ticketDtos;
    }



}

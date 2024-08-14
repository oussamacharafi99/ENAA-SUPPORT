package com.ENAA_SUPPORT.service;
import com.ENAA_SUPPORT.dto.TicketDto;
import com.ENAA_SUPPORT.dto.TicketsTechnicalDescriptionDto;
import com.ENAA_SUPPORT.dto.TicketsTechnicianIdDto;
import com.ENAA_SUPPORT.enums.MaterialEtat;
import com.ENAA_SUPPORT.enums.TicketStatus;
import com.ENAA_SUPPORT.exeptions.EnaaSupportException;
import com.ENAA_SUPPORT.model.*;
import com.ENAA_SUPPORT.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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

    @Autowired
    private TechnicianRepo technicianRepo;

    public List<Ticket> getAllTickets() {
        return ticketRepo.findAll();
    }

    public Ticket getTicketById(int id) {
        return ticketRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Ticket with id " + id + " not found"));
    }

    public void addTicket(Ticket ticket) {
        /*----for update material state------*/
        Material material = materialRepo.findById(ticket.getMaterial().getId()).orElseThrow(() -> new EnaaSupportException("Material not found"));
        Panne panne = panneRepo.findById(ticket.getPanne().getId()).orElseThrow();
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


    public TicketsTechnicianIdDto updateTicketByAdmin(TicketsTechnicianIdDto ticketDto, Integer id) {
        return ticketRepo.findById(id).map(ticket -> {
            Technician technician = technicianRepo.findById(ticketDto.getTicketsTechnicianId())
                    .orElseThrow(() -> new EnaaSupportException("Technician not found"));
            ticket.setTechnician(technician);
            ticket.setStatus(TicketStatus.PROCESSING);
            ticketRepo.save(ticket);
            return ticketDto;
        }).orElseThrow(() -> new EnaaSupportException("Ticket not found"));
    }


    public Ticket updateTicketByTechnicien(TicketsTechnicalDescriptionDto ticket , Integer id) {
        Ticket ticket1 = ticketRepo.findById(id).orElseThrow(() -> new EnaaSupportException("Ticket not found with id " + id));
        ticket1.setStatus(TicketStatus.FIXED);
        ticket1.setTechnicalDescription(ticket.getTechnicalDescription());

        MaterialPanne materialPanne = materialPanneRepo.findByCompositeId(ticket1.getMaterial().getId(),ticket1.getPanne().getId());
            materialPanne.setEndDate(LocalDate.now());
            materialPanne.setDescription(ticket1.getDescription());
            materialPanneRepo.save(materialPanne);

        Material material = materialRepo.findById(ticket1.getMaterial().getId()).orElseThrow();
        material.setEtat(MaterialEtat.REPAIR);
        materialRepo.save(material);

        return ticketRepo.save(ticket1);
    }

    public List<TicketDto> getTicketsByTechnicienId(Integer id) {
        return ticketRepo.findTicketsByTechnicianId(id).stream().map(ticket -> {
            TicketDto dto = new TicketDto();
            dto.setUserName(ticket.getUser().getUsername());
            dto.setDescription(ticket.getDescription());
            dto.setDateCreation(ticket.getDateCreation());
            dto.setStatus(ticket.getStatus());
            dto.setTechnicalDescription(ticket.getTechnicalDescription());
            dto.setMaterialName(ticket.getMaterial().getName());
            dto.setPanneType(ticket.getPanne().getType());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<TicketDto> getTicketsByUserId(Integer id) {
        return ticketRepo.findAllByUserId(id).stream().map(ticket -> {
            TicketDto dto = new TicketDto();
            dto.setId(ticket.getId());
            dto.setUserName(ticket.getUser().getUsername());
            dto.setDateCreation(ticket.getDateCreation());
            dto.setDescription(ticket.getDescription());
            dto.setStatus(ticket.getStatus());
            if (ticket.getTechnician() != null && ticket.getTechnician().getId() != null && ticket.getTechnician().getUsername() != null) {
                dto.setTechnicalDescription(ticket.getTechnicalDescription());
                dto.setUserName(ticket.getTechnician().getUsername());
            }
            dto.setMaterialName(ticket.getMaterial().getName());
            dto.setPanneType(ticket.getPanne().getType());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<TicketDto> TicketStatusFailure(){
        return ticketRepo.findTicketsByStatus(TicketStatus.FAILURE).stream().map(ticket -> {
            TicketDto ticketD = new TicketDto();
            ticketD.setId(ticket.getId());
            ticketD.setUserName(ticket.getUser().getUsername());
            ticketD.setDescription(ticket.getDescription());
            ticketD.setDateCreation(ticket.getDateCreation());
            ticketD.setStatus(ticket.getStatus());
            ticketD.setMaterialName(ticket.getMaterial().getName());
            ticketD.setPanneType(ticket.getPanne().getType());
            return ticketD;
        }).collect(Collectors.toList());
    }

    public List<TicketDto> TicketStatusFixed(){
        return ticketRepo.findTicketsByStatus(TicketStatus.FIXED).stream().map(ticket -> {
            TicketDto ticketD = new TicketDto();
            ticketD.setUserName(ticket.getUser().getUsername());
            ticketD.setDescription(ticket.getDescription());
            ticketD.setDateCreation(ticket.getDateCreation());
            ticketD.setStatus(ticket.getStatus());
            ticketD.setTechnicalName(ticket.getTechnician().getUsername());
            ticketD.setTechnicalDescription(ticket.getTechnicalDescription());
            ticketD.setMaterialName(ticket.getMaterial().getName());
            ticketD.setPanneType(ticket.getPanne().getType());
            return ticketD;
        }).collect(Collectors.toList());
    }

    public List<TicketDto> TicketStatusProcessing(){
        return ticketRepo.findTicketsByStatus(TicketStatus.PROCESSING).stream().map(ticket -> {
            TicketDto ticketD = new TicketDto();
            ticketD.setUserName(ticket.getUser().getUsername());
            ticketD.setDescription(ticket.getDescription());
            ticketD.setDateCreation(ticket.getDateCreation());
            ticketD.setStatus(ticket.getStatus());
            ticketD.setTechnicalName(ticket.getTechnician().getUsername());
            ticketD.setTechnicalDescription(ticket.getTechnicalDescription());
            ticketD.setMaterialName(ticket.getMaterial().getName());
            ticketD.setPanneType(ticket.getPanne().getType());
            return ticketD;
        }).collect(Collectors.toList());
    }


    public List<TicketDto> TicketByTechnicianIdAndStatusProcessing(Integer id){
        return ticketRepo.findTicketsByTechnicianIdAndStatus(id , TicketStatus.PROCESSING).stream().map(ticket -> {
            TicketDto ticketD = new TicketDto();
            ticketD.setId(ticket.getId());
            ticketD.setUserName(ticket.getUser().getUsername());
            ticketD.setDescription(ticket.getDescription());
            ticketD.setDateCreation(ticket.getDateCreation());
            ticketD.setStatus(ticket.getStatus());
            ticketD.setTechnicalName(ticket.getTechnician().getUsername());
            ticketD.setTechnicalDescription(ticket.getTechnicalDescription());
            ticketD.setMaterialName(ticket.getMaterial().getName());
            ticketD.setPanneType(ticket.getPanne().getType());
            return ticketD;
        }).collect(Collectors.toList());
    }

    public List<TicketDto> TicketByTechnicianIdAndStatusFixed(Integer id) {
        return ticketRepo.findTicketsByTechnicianIdAndStatus(id, TicketStatus.FIXED).stream().map(ticket -> {
            TicketDto ticketD = new TicketDto();
            ticketD.setId(ticket.getId());
            ticketD.setUserName(ticket.getUser().getUsername());
            ticketD.setDescription(ticket.getDescription());
            ticketD.setDateCreation(ticket.getDateCreation());
            ticketD.setStatus(ticket.getStatus());
            ticketD.setTechnicalName(ticket.getTechnician().getUsername());
            ticketD.setTechnicalDescription(ticket.getTechnicalDescription());
            ticketD.setMaterialName(ticket.getMaterial().getName());
            ticketD.setPanneType(ticket.getPanne().getType());
            return ticketD;
        }).collect(Collectors.toList());
    }
}

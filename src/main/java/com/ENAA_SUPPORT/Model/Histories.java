//package com.ENAA_SUPPORT.Model;
//
//import com.ENAA_SUPPORT.Enum.MaterialEtat;
//import com.ENAA_SUPPORT.Enum.TicketStatus;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import java.time.LocalDate;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//public class Histories {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
////    ----------material----------
//    private Integer material_id;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate insert_material_date;
//
//    @Enumerated(EnumType.STRING)
//    private MaterialEtat start_material_etat;
//    @Enumerated(EnumType.STRING)
//    private MaterialEtat processing_material_etat;
//    @Enumerated(EnumType.STRING)
//    private MaterialEtat fixed_material_etat;
////    --------- ticket ------------
//    private Integer ticket_id;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate date_create_ticket;
//    @Enumerated(EnumType.STRING)
//    private TicketStatus start_ticket_status;
//    @Enumerated(EnumType.STRING)
//    private TicketStatus processing_ticket_status;
//    @Enumerated(EnumType.STRING)
//    private TicketStatus fixed_ticket_status;
//    private Integer technician_id;
//    private String technician_description;
//
//}

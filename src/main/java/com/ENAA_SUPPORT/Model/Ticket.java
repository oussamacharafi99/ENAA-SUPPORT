package com.ENAA_SUPPORT.Model;
import com.ENAA_SUPPORT.Enum.TicketStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column()
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column()
    private LocalDate dateCreation;

    @Column()
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @Column()
    private String TechnicalDescription;

    @ManyToOne
    @JoinColumn(name = "panne_id")
    private Panne panne;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Technician technician;

    @ManyToOne
    @JoinColumn(name = "Material_id")
    private Material material;

}

package com.ENAA_SUPPORT.Model;

import com.ENAA_SUPPORT.Enum.PanneType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Panne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column()
    private LocalDate date;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "panne")
    @JsonIgnore
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "panne")
    @JsonIgnore
    private List<MaterialPanne> materialPannes;
}

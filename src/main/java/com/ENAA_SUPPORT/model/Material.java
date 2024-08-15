package com.ENAA_SUPPORT.model;

import com.ENAA_SUPPORT.enums.MaterialEtat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Material {

    public Material(String name, String description, MaterialEtat etat, LocalDate insert_date, User user) {
        this.name = name;
        this.description = description;
        this.etat = etat;
        this.insert_date = insert_date;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column()
    private String name;

    @Column()
    private String description;

    @Column()
    @Enumerated(EnumType.STRING)
    private MaterialEtat etat;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column()
    private LocalDate insert_date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "material")
    @JsonIgnore
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "material")
    @JsonIgnore
    private List<MaterialPanne> materialPannes;
}

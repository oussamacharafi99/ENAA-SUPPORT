package com.ENAA_SUPPORT.Model;

import com.ENAA_SUPPORT.Enum.MaterialEtat;
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
    private List<Panne> pannes;

}

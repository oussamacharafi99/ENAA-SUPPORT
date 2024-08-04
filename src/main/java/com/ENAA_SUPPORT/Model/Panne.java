package com.ENAA_SUPPORT.Model;

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
public class Panne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column()
    private LocalDate date;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private  Material material;
}

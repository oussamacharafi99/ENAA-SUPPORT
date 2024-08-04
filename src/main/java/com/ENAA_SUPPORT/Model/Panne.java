package com.ENAA_SUPPORT.Model;

import jakarta.persistence.*;
import lombok.*;

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
    private String description;
    private String date;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private  Material material;
}

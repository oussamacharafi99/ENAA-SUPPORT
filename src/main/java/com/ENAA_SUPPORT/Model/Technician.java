package com.ENAA_SUPPORT.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@DiscriminatorValue("TECHNICIAN")
public class Technician extends Person {

    @OneToMany(mappedBy = "technician")
    @JsonIgnore
    private List<Ticket> tickets;

}

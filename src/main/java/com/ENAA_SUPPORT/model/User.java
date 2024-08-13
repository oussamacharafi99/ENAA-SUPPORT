package com.ENAA_SUPPORT.model;

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
@DiscriminatorValue("USER")
public class User extends Person {

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Material> materials;
}

package com.ENAA_SUPPORT.dto;

import com.ENAA_SUPPORT.enums.TicketStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    private Integer id;

    private String userName;

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateCreation;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    private String technicalName;

    private String technicalDescription;

    private  String materialName;

    private String panneType;

}

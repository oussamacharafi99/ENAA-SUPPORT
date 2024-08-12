package com.ENAA_SUPPORT.Dto;

import com.ENAA_SUPPORT.Enum.PanneType;
import com.ENAA_SUPPORT.Enum.TicketStatus;
import jakarta.persistence.Column;
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

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateCreation;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    private String technicalName;

    private String technicalDescription;

    private  String materialName;

    private PanneType panneType;

}

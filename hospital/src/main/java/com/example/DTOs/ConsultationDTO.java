package com.example.DTOs;

import com.example.Entities.RendezVous;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class ConsultationDTO {

    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dateConsultation;
    private String rapport;
    private RendezVous rendezVous;
}

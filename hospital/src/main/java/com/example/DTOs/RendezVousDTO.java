package com.example.DTOs;

import com.example.Entities.Consultation;
import com.example.Entities.Medecin;
import com.example.Entities.Patient;
import com.example.Entities.StatusRDV;
import lombok.Data;

import java.util.Date;

@Data
public class RendezVousDTO {

    private String idRDV;
    private Date dateRDV;
    private StatusRDV status;

    private Patient patient;

    private Medecin medecin;

    private Consultation consultation;
}

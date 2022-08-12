package com.example.DTOs;

import com.example.Entities.RendezVous;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@Data
public class PatientDTO {

    private Long idPatient;
    @NotNull(message = "Name does not null")
    private String name;
    @Email(message = "Email is not Valid")
    private String mail;
    private Date dateNaissance;
    private Boolean malade;
    private Collection<RendezVous> rendezVousPatient;
}
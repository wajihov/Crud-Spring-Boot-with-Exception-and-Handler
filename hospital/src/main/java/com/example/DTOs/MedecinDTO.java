package com.example.DTOs;

import com.example.Entities.RendezVous;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Getter
@Setter
public class MedecinDTO {

    private Long idMedecin;
    @NotBlank(message = "Name does not Null")
    private String name;
    @Email(message = "Mail not Valid")
    private String email;
    @NotNull(message = "Le medecin doit avoir une spécialité. ")
    private String speciality;
    private Collection<RendezVous> rendezVousMedecin;
}
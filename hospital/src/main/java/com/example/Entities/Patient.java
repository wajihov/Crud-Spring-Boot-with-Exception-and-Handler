package com.example.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPatient;
    @Column(length = 80)
    @NotBlank(message = "The name is not valid")
    private String name;
    @Email(message = "Mail not Valid")
    private String mail;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private Boolean malade;
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private Collection<RendezVous> rendezVousPatient;


}

package com.example;

import com.example.DTOs.MedecinDTO;
import com.example.DTOs.PatientDTO;
import com.example.DTOs.RendezVousDTO;
import com.example.Entities.*;
import com.example.Exception.MedecinNotFoundException;
import com.example.Mapper.ConsultationMapperImpl;
import com.example.Mapper.MedecinMapperImpl;
import com.example.Mapper.PatientMapperImpl;
import com.example.Mapper.RendezVousMapper;
import com.example.Services.Ihospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication {

    @Autowired
    private PatientMapperImpl patientMapper;
    @Autowired
    private MedecinMapperImpl medecinMapper;
    @Autowired
    private RendezVousMapper rendezVousMapper;
    @Autowired
    private ConsultationMapperImpl consultationMapper;

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

    @Bean
    CommandLineRunner Start(Ihospital hospitalService) {
        return args -> {
            Stream.of("hassen", "hajer", "Ali", "rabiaa").forEach(name -> {
                MedecinDTO medecinDTO = new MedecinDTO();
                Medecin medecin = medecinMapper.fromMedecinDTO(medecinDTO);
                medecin.setName(name);
                medecin.setEmail(name + "@hotmail.com");
                medecin.setSpeciality(Math.random() > 0.5 ? "Generaliste" : "Cardio");

                hospitalService.saveMedecin(medecinMapper.fromMedecin(medecin));
            });
            Stream.of("Mohamed", "Najet", "Saleh", "Rathi").forEach(name -> {
                PatientDTO patientDTO = new PatientDTO();
                Patient patient = patientMapper.fromPatientDTO(patientDTO);
                patient.setName(name);
                patient.setMail(name + "@gmail.com");
                patient.setMalade(true);
                patient.setDateNaissance(new Date());

                hospitalService.savePatient(patientMapper.fromPatient(patient));
            });

            PatientDTO patientDTO = hospitalService.findPatienById(4L);
            List<PatientDTO> patients = hospitalService.findPatientByName("Saleh");
            List<Patient> pts = patients.stream().map(pt -> patientMapper.fromPatientDTO(pt)).collect(Collectors.toList());
            pts.forEach(p -> System.out.println("le nom de patient " + p.getName() + "   " + p.getMail()));
            MedecinDTO medecin = null;
            List<Medecin> mds = null;
            try {
                medecin = hospitalService.findMedecinById(1L);
            } catch (MedecinNotFoundException e) {
                e.printStackTrace();
            }
            try {
                List<MedecinDTO> medecins = hospitalService.findMedecinByName("Ali");
                mds = medecins.stream().map(med -> medecinMapper.fromMedecinDTO(med)).collect(Collectors.toList());
                mds.forEach(m -> System.out.println("Le medecins rechercher est : " + m.getName() + " " + m.getEmail()));

            } catch (com.example.Exception.MedecinNotFoundException e) {
                e.printStackTrace();
            }


            RendezVous rendezVous = new RendezVous();
            Medecin medecin1 = medecinMapper.fromMedecinDTO(medecin);
            rendezVous.setMedecin(medecin1);
            rendezVous.setPatient(pts.get(0));
            rendezVous.setDateRDV(new Date());
            rendezVous.setStatus(StatusRDV.Encours);

            RendezVousDTO rendezVousDTO = rendezVousMapper.fromRendezVous(rendezVous);
            RendezVousDTO saveRendezVous = hospitalService.saveRDV(rendezVousDTO);
            System.out.println("la date de RDV EST : " + saveRendezVous.getDateRDV());
            System.out.println("Le numero de RDV : " + saveRendezVous.getIdRDV());

            List<RendezVousDTO> rendezVous1 = hospitalService.findAllRDV();
            List<RendezVous> rendezVous2 = rendezVous1.stream().map(r -> rendezVousMapper.fromRendeVousDTO(r)).collect(Collectors.toList());
            rendezVous2.forEach(d -> System.out.println(d.getIdRDV() + " " + d.getStatus()));
            Consultation consultation = new Consultation();
            consultation.setDateConsultation(new Date());

            consultation.setRendezVous(rendezVousMapper.fromRendeVousDTO(saveRendezVous));
            consultation.setRapport("le rapport est bien fini ....");
            hospitalService.saveConsultation(consultationMapper.fromConsultation(consultation));
        };
    }

}

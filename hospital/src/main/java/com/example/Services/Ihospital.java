package com.example.Services;

import com.example.DTOs.ConsultationDTO;
import com.example.DTOs.MedecinDTO;
import com.example.DTOs.PatientDTO;
import com.example.DTOs.RendezVousDTO;
import com.example.Exception.ConsultationExecptionNotFound;
import com.example.Exception.MedecinNotFoundException;
import com.example.Exception.PatientNotFoundException;
import com.example.Exception.RendezVousNotFound;

import java.util.List;
import java.util.Map;

public interface Ihospital {

    MedecinDTO saveMedecin(MedecinDTO medecinDTO);

    List<MedecinDTO> getAllMedecins();

    MedecinDTO getMedecin(Long id) throws MedecinNotFoundException;

    PatientDTO savePatient(PatientDTO patientDTO);

    RendezVousDTO saveRDV(RendezVousDTO rendezVousDTO);


    PatientDTO findPatienById(Long id) throws PatientNotFoundException;

    List<PatientDTO> findPatientByName(String name) throws PatientNotFoundException;

    List<PatientDTO> searchByName(String name) throws PatientNotFoundException;

    List<PatientDTO> searchByNameII(String name) throws PatientNotFoundException;

    MedecinDTO findMedecinById(Long id) throws MedecinNotFoundException;

    List<MedecinDTO> findMedecinByName(String name) throws MedecinNotFoundException;

    RendezVousDTO findRDVById(String id) throws RendezVousNotFound;

    List<PatientDTO> allPatient() throws PatientNotFoundException;

    List<RendezVousDTO> findAllRDV() throws RendezVousNotFound;

    ConsultationDTO saveConsultation(ConsultationDTO consultationDTO);

    ConsultationDTO findConsultationByID(Long id) throws ConsultationExecptionNotFound;

    List<ConsultationDTO> getAllConsultations() throws ConsultationExecptionNotFound;

    ConsultationDTO updateConsultation(ConsultationDTO consultationDTO, Long id) throws ConsultationExecptionNotFound;

    MedecinDTO updateMedecin(MedecinDTO medecinDTO, Long id) throws MedecinNotFoundException, RendezVousNotFound;

    PatientDTO upDatePatient(PatientDTO patientDTO, Long id) throws PatientNotFoundException;

    RendezVousDTO updateRendezVous(RendezVousDTO rendezVousDTO, String id) throws RendezVousNotFound;

    Map<String, Boolean> deletePatient(Long id) throws PatientNotFoundException;

    Map<String, Boolean> deleteMedecin(Long id) throws MedecinNotFoundException;

    Map<String, Boolean> deleteRDV(String id) throws RendezVousNotFound;
}

package com.example.Services;

import com.example.DTOs.ConsultationDTO;
import com.example.DTOs.MedecinDTO;
import com.example.DTOs.PatientDTO;
import com.example.DTOs.RendezVousDTO;
import com.example.Entities.Consultation;
import com.example.Entities.Medecin;
import com.example.Entities.Patient;
import com.example.Entities.RendezVous;
import com.example.Exception.ConsultationExecptionNotFound;
import com.example.Exception.MedecinNotFoundException;
import com.example.Exception.PatientNotFoundException;
import com.example.Exception.RendezVousNotFound;
import com.example.Mapper.ConsultationMapperImpl;
import com.example.Mapper.MedecinMapperImpl;
import com.example.Mapper.PatientMapperImpl;
import com.example.Mapper.RendezVousMapper;
import com.example.Repositories.ConsultationRepository;
import com.example.Repositories.MedecinRepository;
import com.example.Repositories.PatientRepository;
import com.example.Repositories.RendezVousRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class IhospitalImpl implements Ihospital {

    Logger logger = LoggerFactory.getLogger(IhospitalImpl.class);

    @Autowired
    private PatientMapperImpl patientMapper;
    @Autowired
    private MedecinMapperImpl medecinMapper;
    @Autowired
    private RendezVousMapper rendezVousMapper;
    @Autowired
    private ConsultationMapperImpl consultationMapper;

    private MedecinRepository medecinRepository;
    private PatientRepository patientRepository;
    private ConsultationRepository consultationRepository;
    private RendezVousRepository rendezVousRepository;

    public IhospitalImpl(MedecinRepository medecinRepository, PatientRepository patientRepository,
                         ConsultationRepository consultationRepository, RendezVousRepository rendezVousRepository) {
        this.medecinRepository = medecinRepository;
        this.patientRepository = patientRepository;
        this.consultationRepository = consultationRepository;
        this.rendezVousRepository = rendezVousRepository;
    }

    @Override
    public PatientDTO savePatient(PatientDTO patientDTO) {
        logger.info("saving patient");
        Patient patient = patientMapper.fromPatientDTO(patientDTO);
        Patient savePatient = patientRepository.save(patient);
        return patientMapper.fromPatient(savePatient);
    }

    @Override
    public MedecinDTO saveMedecin(MedecinDTO medecinDTO) {
        logger.info("Saving medecine");
        Medecin medecin = medecinMapper.fromMedecinDTO(medecinDTO);
        Medecin savedmedecin = medecinRepository.save(medecin);
        return medecinMapper.fromMedecin(savedmedecin);
    }

    @Override
    public List<MedecinDTO> getAllMedecins() {
        logger.info("Get All Medecins");
        List<Medecin> medecins = medecinRepository.findAll();
        List<MedecinDTO> medecinDTOS = medecins.stream().map(medecin -> medecinMapper.fromMedecin(medecin)).collect(Collectors.toList());
        return medecinDTOS;
    }

    @Override
    public MedecinDTO getMedecin(Long id) throws MedecinNotFoundException {
        Medecin medecin = medecinRepository.findById(id).orElseThrow(() -> new MedecinNotFoundException("Medecin not found with id : " + id));
        return medecinMapper.fromMedecin(medecin);
    }

    @Override
    public MedecinDTO findMedecinById(Long id) throws MedecinNotFoundException {
        Medecin medecin = medecinRepository.findById(id).orElseThrow(() -> new MedecinNotFoundException("Medecin not found with id  : " + id));
        return medecinMapper.fromMedecin(medecin);
    }

    @Override
    public List<MedecinDTO> findMedecinByName(String name) throws MedecinNotFoundException {
        List<Medecin> medecins = medecinRepository.findByName(name);
        if (medecins == null) {
            throw new MedecinNotFoundException("Medecin Not Found");
        }
        List<MedecinDTO> medecinDTOS = medecins.stream().map(medecin -> medecinMapper.fromMedecin(medecin)).collect(Collectors.toList());
        return medecinDTOS;
    }


    @Override
    public RendezVousDTO saveRDV(RendezVousDTO rendezVousDTO) {
        logger.info("saving RDV");
        RendezVous rendezVous = rendezVousMapper.fromRendeVousDTO(rendezVousDTO);
        rendezVous.setIdRDV(UUID.randomUUID().toString());
        RendezVousDTO savedRendezVous = rendezVousMapper.fromRendezVous(rendezVousRepository.save(rendezVous));
        return savedRendezVous;
    }

    @Override
    public PatientDTO findPatienById(Long id) throws PatientNotFoundException {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found with id : " + id));
        return patientMapper.fromPatient(patient);
    }

    @Override
    public List<PatientDTO> findPatientByName(String name) throws PatientNotFoundException {
        List<Patient> patients = patientRepository.findByName(name);
        if (patients.isEmpty()) {
            throw new PatientNotFoundException("Patient not found with name is : " + name);
        }
        List<PatientDTO> patientDTOS = patients.stream().map(patient -> patientMapper.fromPatient(patient)).collect(Collectors.toList());
        return patientDTOS;
    }

    @Override
    public List<PatientDTO> searchByName(String name) throws PatientNotFoundException {
        List<Patient> patients = patientRepository.searchByName(name);
        if (patients.isEmpty()) {
            throw new PatientNotFoundException("Patient not found with name is : " + name);
        }
        List<PatientDTO> patientDTOS = patients.stream().map(patient -> patientMapper.fromPatient(patient)).collect(Collectors.toList());
        return patientDTOS;
    }

    @Override
    public List<PatientDTO> searchByNameII(String name) throws PatientNotFoundException {
        List<Patient> patients = patientRepository.searchByNameII(name);
        if (patients.isEmpty()) {
            throw new PatientNotFoundException("Patient not found with name is : " + name);
        }
        List<PatientDTO> patientDTOS = patients.stream().map(patient -> patientMapper.fromPatient(patient)).collect(Collectors.toList());
        return patientDTOS;
    }


    @Override
    public RendezVousDTO findRDVById(String id) throws RendezVousNotFound {
        RendezVous rendezVous = rendezVousRepository.findById(id).orElseThrow(() -> new RendezVousNotFound("RDV not found with id : " + id));
        RendezVousDTO rendezVousDTO = rendezVousMapper.fromRendezVous(rendezVous);
        return rendezVousDTO;
    }

    @Override
    public List<PatientDTO> allPatient() throws PatientNotFoundException {
        List<Patient> patients = patientRepository.findAll();
        if (patients.isEmpty()) {
            throw new PatientNotFoundException("Do not have Patients in the list");
        }
        List<PatientDTO> patientDTOS = patients.stream().map(pat ->
                patientMapper.fromPatient(pat)).collect(Collectors.toList());
        return patientDTOS;
    }

    @Override
    public List<RendezVousDTO> findAllRDV() throws RendezVousNotFound {
        List<RendezVous> rendezVous = rendezVousRepository.findAll();
        if (rendezVous.isEmpty()) {
            throw new RendezVousNotFound("Do not have RDV");
        }
        List<RendezVousDTO> rendezVousDTOS = rendezVous.stream().map(rdv -> rendezVousMapper.fromRendezVous(rdv)).collect(Collectors.toList());
        return rendezVousDTOS;
    }

    @Override
    public ConsultationDTO saveConsultation(ConsultationDTO consultationDTO) {
        Consultation consultation = consultationMapper.fromConsultationDTO(consultationDTO);
        ConsultationDTO savedConsultation = consultationMapper.fromConsultation(consultationRepository.save(consultation));
        return savedConsultation;
    }

    @Override
    public ConsultationDTO findConsultationByID(Long id) throws ConsultationExecptionNotFound {
        Consultation consultation = consultationRepository.findById(id).orElseThrow(() -> new ConsultationExecptionNotFound("Consultaion not with number : " + id));
        return consultationMapper.fromConsultation(consultation);
    }

    @Override
    public List<ConsultationDTO> getAllConsultations() throws ConsultationExecptionNotFound {
        List<Consultation> consultations = consultationRepository.findAll();
        if (consultations.isEmpty()) {
            throw new ConsultationExecptionNotFound("No Consultations Sorry ");
        }
        List<ConsultationDTO> consultationDTOS = consultations.stream().map(consultation -> consultationMapper.fromConsultation(consultation)).collect(Collectors.toList());
        return consultationDTOS;
    }

    @Override
    public ConsultationDTO updateConsultation(ConsultationDTO consultationDTO, Long id) throws ConsultationExecptionNotFound {
        Consultation consultation = consultationMapper.fromConsultationDTO(consultationDTO);
        Consultation currentConsultation = consultationRepository.findById(id).orElseThrow(() -> new ConsultationExecptionNotFound("Sorry id not Found : " + id));
        if (consultation.getRapport() != null) {
            currentConsultation.setRapport(consultation.getRapport());
        }
        if (consultation.getRendezVous() != null) {
            currentConsultation.setRendezVous(consultation.getRendezVous());
        }
        if (consultation.getDateConsultation() != null) {
            currentConsultation.setDateConsultation(consultation.getDateConsultation());
        }
        ConsultationDTO savedConsultationDTO = consultationMapper.fromConsultation(consultationRepository.save(currentConsultation));
        return savedConsultationDTO;
    }

    @Override
    public MedecinDTO updateMedecin(MedecinDTO medecinDTO, Long id) throws MedecinNotFoundException, RendezVousNotFound {
        Medecin medecin = medecinMapper.fromMedecinDTO(medecinDTO);
        Medecin currentMedecin = medecinMapper.fromMedecinDTO(findMedecinById(id));
        if (!medecin.getEmail().isEmpty()) {
            currentMedecin.setEmail(medecin.getEmail());
        }
        if (!medecin.getName().isEmpty()) {
            currentMedecin.setName(medecin.getName());
        }
        if (medecin.getRendezVousMedecin() != null) {
            //currentMedecin.setRendezVousMedecin(medecin.getRendezVousMedecin());
            RendezVousDTO vousDTO = (RendezVousDTO) medecin.getRendezVousMedecin();
            updateRendezVous(vousDTO, ((RendezVousDTO) medecin.getRendezVousMedecin()).getIdRDV());
        }
        if (!medecin.getSpeciality().isEmpty()) {
            currentMedecin.setSpeciality(medecin.getSpeciality());
        }
        MedecinDTO savedMedecinDTO = medecinMapper.fromMedecin(medecinRepository.save(currentMedecin));
        return savedMedecinDTO;
    }

    @Override
    public PatientDTO upDatePatient(PatientDTO patientDTO, Long id) throws PatientNotFoundException {
        Patient patient = patientMapper.fromPatientDTO(patientDTO);
        Patient currentPatient = patientMapper.fromPatientDTO(findPatienById(id));
        if (patient.getDateNaissance() != null) {
            currentPatient.setDateNaissance(patient.getDateNaissance());
        }
        if (patient.getMail() != null) {
            currentPatient.setMail(patient.getMail());
        }
        if (patient.getName() != null) {
            currentPatient.setName(patient.getName());
        }
        if (patient.getRendezVousPatient() != null) {
            currentPatient.setRendezVousPatient(patient.getRendezVousPatient());
        }
        if (patient.getMalade()) {
            currentPatient.setMalade(patient.getMalade());
        }
        PatientDTO savedPatient = patientMapper.fromPatient(patientRepository.save(currentPatient));
        return savedPatient;
    }

    @Override
    public RendezVousDTO updateRendezVous(RendezVousDTO rendezVousDTO, String id) throws RendezVousNotFound {
        RendezVous rendezVous = rendezVousMapper.fromRendeVousDTO(rendezVousDTO);
        RendezVous currentRendezVous = rendezVousMapper.fromRendeVousDTO(findRDVById(id));
        if (rendezVous.getConsultation() != null) {
            currentRendezVous.setConsultation(rendezVous.getConsultation());
        }
        if (rendezVous.getDateRDV() != null) {
            currentRendezVous.setDateRDV(rendezVous.getDateRDV());
        }
        if (rendezVous.getMedecin() != null) {
            currentRendezVous.setMedecin(rendezVous.getMedecin());
        }
        if (rendezVous.getPatient() != null) {
            currentRendezVous.setPatient(rendezVous.getPatient());
        }
        if (rendezVous.getStatus() != null) {
            currentRendezVous.setStatus(rendezVous.getStatus());
        }
        RendezVousDTO savedRDV = rendezVousMapper.fromRendezVous(rendezVousRepository.save(currentRendezVous));
        return savedRDV;
    }


    @Override
    public Map<String, Boolean> deletePatient(Long id) throws PatientNotFoundException {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient Not Found with id : " + id));
        patientRepository.delete(patient);
        Map<String, Boolean> mapDelete = new HashMap<>();
        mapDelete.put("Delete Patient", Boolean.TRUE);
        return mapDelete;
    }

    @Override
    public Map<String, Boolean> deleteMedecin(Long id) throws MedecinNotFoundException {
        MedecinDTO medecinDTO = getMedecin(id);
        Medecin medecin = medecinMapper.fromMedecinDTO(medecinDTO);
        medecinRepository.delete(medecin);
        Map<String, Boolean> mapDelete = new HashMap<>();
        mapDelete.put("Delete Medecin", Boolean.TRUE);
        return mapDelete;
    }

    @Override
    public Map<String, Boolean> deleteRDV(String id) throws RendezVousNotFound {
        RendezVousDTO rendezVousDTO = findRDVById(id);
        rendezVousRepository.delete(rendezVousMapper.fromRendeVousDTO(rendezVousDTO));
        Map<String, Boolean> mapDelete = new HashMap<>();
        mapDelete.put("Delete Rendez vous", Boolean.TRUE);
        return mapDelete;
    }
}

package com.example.Mapper;

import com.example.DTOs.PatientDTO;
import com.example.Entities.Patient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PatientMapperImpl {

    public PatientDTO fromPatient(Patient patient) {
        PatientDTO patientDTO = new PatientDTO();
        BeanUtils.copyProperties(patient, patientDTO);
        return patientDTO;
    }

    public Patient fromPatientDTO(PatientDTO patientDTO) {
        Patient patient = new Patient();
        BeanUtils.copyProperties(patientDTO, patient);
        return patient;
    }
}
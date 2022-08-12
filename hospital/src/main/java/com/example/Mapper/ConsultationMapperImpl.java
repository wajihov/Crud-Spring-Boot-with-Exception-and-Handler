package com.example.Mapper;

import com.example.DTOs.ConsultationDTO;
import com.example.Entities.Consultation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ConsultationMapperImpl {

    public Consultation fromConsultationDTO(ConsultationDTO consultationDTO) {
        Consultation consultation = new Consultation();
        BeanUtils.copyProperties(consultationDTO, consultation);
        return consultation;
    }

    public ConsultationDTO fromConsultation(Consultation consultation) {
        ConsultationDTO consultationDTO = new ConsultationDTO();
        BeanUtils.copyProperties(consultation, consultationDTO);
        return consultationDTO;
    }
}

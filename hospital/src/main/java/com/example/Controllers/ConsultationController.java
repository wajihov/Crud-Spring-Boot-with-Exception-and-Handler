package com.example.Controllers;

import com.example.DTOs.ConsultationDTO;
import com.example.Exception.ConsultationExecptionNotFound;
import com.example.Mapper.ConsultationMapperImpl;
import com.example.Services.Ihospital;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/c1")
public class ConsultationController {

    Ihospital ihospital;
    ConsultationMapperImpl consultationMapper;


    @PostMapping(value = "/consultations")
    public ResponseEntity<ConsultationDTO> saveConsultation(@RequestBody ConsultationDTO consultationDTO) {
        return new ResponseEntity(ihospital.saveConsultation(consultationDTO), HttpStatus.CREATED);
    }

    @GetMapping("/consultations/{id}")
    public ResponseEntity<ConsultationDTO> getConsultationById(@PathVariable("id") Long id) throws ConsultationExecptionNotFound {
        return ResponseEntity.ok(ihospital.findConsultationByID(id));
    }

    @GetMapping("/consultations")
    public ResponseEntity<List<ConsultationDTO>> getAllConsultations() throws ConsultationExecptionNotFound {
        return ResponseEntity.ok(ihospital.getAllConsultations());
    }

    @PutMapping("/consultations/{id}")
    public ResponseEntity<ConsultationDTO> updateConsultation(@RequestBody ConsultationDTO consultationDTO, @PathVariable Long id) throws ConsultationExecptionNotFound {
        return new ResponseEntity<>(ihospital.updateConsultation(consultationDTO, id), HttpStatus.ACCEPTED);
    }

}

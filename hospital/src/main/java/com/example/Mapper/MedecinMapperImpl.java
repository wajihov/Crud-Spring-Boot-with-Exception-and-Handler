package com.example.Mapper;

import com.example.DTOs.MedecinDTO;
import com.example.Entities.Medecin;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MedecinMapperImpl {

    public Medecin fromMedecinDTO(MedecinDTO medecinDTO) {
        Medecin medecin = new Medecin();
        BeanUtils.copyProperties(medecinDTO, medecin);
        return medecin;
    }

    public MedecinDTO fromMedecin(Medecin medecin) {
        MedecinDTO medecinDTO = new MedecinDTO();
        BeanUtils.copyProperties(medecin, medecinDTO);
        return medecinDTO;
    }

}
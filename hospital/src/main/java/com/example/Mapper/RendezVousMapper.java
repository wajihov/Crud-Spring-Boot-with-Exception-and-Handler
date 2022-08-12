package com.example.Mapper;

import com.example.DTOs.RendezVousDTO;
import com.example.Entities.RendezVous;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RendezVousMapper {

    public RendezVousDTO fromRendezVous(RendezVous rendezVous) {
        RendezVousDTO rendezVousDTO = new RendezVousDTO();
        BeanUtils.copyProperties(rendezVous, rendezVousDTO);
        return rendezVousDTO;
    }

    public RendezVous fromRendeVousDTO(RendezVousDTO rendezVousDTO) {
        RendezVous rendezVous = new RendezVous();
        BeanUtils.copyProperties(rendezVousDTO, rendezVous);
        return rendezVous;
    }
}

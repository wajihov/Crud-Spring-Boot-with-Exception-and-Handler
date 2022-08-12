package com.example.Controllers;

import com.example.DTOs.MedecinDTO;
import com.example.Exception.MedecinNotFoundException;
import com.example.Exception.RendezVousNotFound;
import com.example.Services.Ihospital;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/m1")
public class MedecinController {

    private Ihospital ihospital;

    @PostMapping("/medecins")
    public ResponseEntity<MedecinDTO> saveMdecin(@RequestBody @Valid MedecinDTO medecinDTO) {
        return new ResponseEntity<>(ihospital.saveMedecin(medecinDTO), HttpStatus.CREATED);
    }

    @GetMapping("/medecins")
    public ResponseEntity<List<MedecinDTO>> allMedecin() {
        return ResponseEntity.ok(ihospital.getAllMedecins());
    }

    @GetMapping("/medecinS/{id}")
    public ResponseEntity<MedecinDTO> getMedecinById(@PathVariable("id") Long id) throws MedecinNotFoundException {
        return ResponseEntity.ok(ihospital.getMedecin(id));
    }

    @GetMapping("/FindMedecins/{id}")
    public ResponseEntity<MedecinDTO> findMedecinById(@PathVariable("id") Long id) throws MedecinNotFoundException {
        return new ResponseEntity<>(ihospital.findMedecinById(id), HttpStatus.OK);
    }

    @GetMapping("/medecins/{name}")
    public ResponseEntity<List<MedecinDTO>> getMedecinByName(@PathVariable("name") String name) throws MedecinNotFoundException {
        return new ResponseEntity<>(ihospital.findMedecinByName(name), HttpStatus.OK);
    }

    @PutMapping("/medecins/{id}")
    public ResponseEntity<MedecinDTO> modifyMedecinDTO(@PathVariable("id") Long id, @RequestBody MedecinDTO medecinDTO) throws MedecinNotFoundException, RendezVousNotFound {
        return new ResponseEntity<>(ihospital.updateMedecin(medecinDTO, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteMedecins/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePatient(@PathVariable("id") Long id) throws MedecinNotFoundException {
        Map<String, Boolean> response = ihospital.deleteMedecin(id);
        return ResponseEntity.ok(response);
    }
}

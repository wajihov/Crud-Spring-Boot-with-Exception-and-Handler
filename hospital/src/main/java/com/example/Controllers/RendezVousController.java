package com.example.Controllers;

import com.example.DTOs.RendezVousDTO;
import com.example.Exception.RendezVousNotFound;
import com.example.Services.Ihospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rdv")
public class RendezVousController {

    @Autowired
    private Ihospital ihospital;

    @GetMapping("/rendezVousById/{id}")
    public ResponseEntity<RendezVousDTO> getRDV(@PathVariable("id") String id) throws RendezVousNotFound {
        return new ResponseEntity<>(ihospital.findRDVById(id), HttpStatus.OK);
    }

    @GetMapping("/rendezVous")
    public ResponseEntity<List<RendezVousDTO>> getAllRDV() throws RendezVousNotFound {
        return new ResponseEntity(ihospital.findAllRDV(), HttpStatus.OK);
    }

    @PostMapping("/rendezVousPost")
    public ResponseEntity<RendezVousDTO> saveRDV(@RequestBody RendezVousDTO rendezVousDTO) {
        return new ResponseEntity<>(ihospital.saveRDV(rendezVousDTO), HttpStatus.CREATED);
    }

    @PutMapping("rendezVousPut/{id}")
    public ResponseEntity<RendezVousDTO> updateRendezVous(@RequestBody RendezVousDTO rendezVousDTO, @PathVariable("id") String id) throws RendezVousNotFound {
        return ResponseEntity.ok(ihospital.updateRendezVous(rendezVousDTO, id));
    }

    @DeleteMapping("/rendezVousDelete/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteRDV(@PathVariable("id") String id) throws RendezVousNotFound {
        Map<String, Boolean> response = ihospital.deleteRDV(id);
        return ResponseEntity.ok(response);
    }


}

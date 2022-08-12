package com.example.Controllers;

import com.example.DTOs.PatientDTO;
import com.example.Exception.PatientNotFoundException;
import com.example.Services.Ihospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/p1")
public class PatientController {

    @Autowired
    private Ihospital ihospital;

    @GetMapping("/patients")
    public ResponseEntity<List<PatientDTO>> getAllPatient() throws PatientNotFoundException {
        return ResponseEntity.ok(ihospital.allPatient());
    }

    @GetMapping("/patientById")
    public ResponseEntity<PatientDTO> getPatientById(@RequestParam("id") Long id) throws PatientNotFoundException {
        return ResponseEntity.ok(ihospital.findPatienById(id));
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<PatientDTO> getPatientByIdOther(@PathVariable("id") Long id) throws PatientNotFoundException {
        return ResponseEntity.ok(ihospital.findPatienById(id));
    }

    @GetMapping(value = "/patientByName")
    public ResponseEntity<List<PatientDTO>> getPationtByName(@RequestParam("name") String name) throws PatientNotFoundException {
        return ResponseEntity.ok(ihospital.findPatientByName(name));
    } // http://localhost:8081/api/v1/patientByName?name=Najet

    @GetMapping("/patientsAnotherWay/name")
    public ResponseEntity<List<PatientDTO>> getPationtByNameAnotherWay(@RequestParam("name") String name) throws PatientNotFoundException {
        return new ResponseEntity<>(ihospital.findPatientByName(name), HttpStatus.OK);
    } //http://localhost:8081/api/v1/patientsAnotherWay/name?name=Najet

    @GetMapping("/patientsByQuery/{name}")
    public ResponseEntity<List<PatientDTO>> findPationtByQuery(@PathVariable("name") String name) throws PatientNotFoundException {
        return new ResponseEntity<>(ihospital.searchByName(name), HttpStatus.OK);
    } // http://localhost:8081/api/v1/patientsByQuery/amed

    @GetMapping("/patientsByQueryII/{name}")
    public ResponseEntity<List<PatientDTO>> findPationtByQueryII(@PathVariable("name") String name) throws PatientNotFoundException {
        return new ResponseEntity<>(ihospital.searchByNameII(name), HttpStatus.OK);
    } // http://localhost:8081/api/v1/patientsByQuery/h


    @PostMapping("/patients")
    public ResponseEntity<PatientDTO> addPatient(@RequestBody @Valid PatientDTO patientDTO) {
        return ResponseEntity.ok(ihospital.savePatient(patientDTO));
    }

    //A refaire
    @PutMapping("/UpdatePatients/{id}")
    public ResponseEntity<PatientDTO> modifyPatient(@RequestBody @Valid PatientDTO patientDTO, @PathVariable("id") Long id) throws PatientNotFoundException {
        System.out.println("Le patient : " + patientDTO.getName() + " Name  " + patientDTO.getMail() + " " + patientDTO.getDateNaissance());
        return new ResponseEntity<>(ihospital.upDatePatient(patientDTO, id), HttpStatus.OK);
    }


    @DeleteMapping("/patients/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePatient(@PathVariable("id") Long id) throws PatientNotFoundException {
        Map<String, Boolean> response = ihospital.deletePatient(id);
        return ResponseEntity.ok(response);
    }

}

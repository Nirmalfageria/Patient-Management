package org.fageria.patientservice.controller;

import jakarta.validation.Valid;
import org.fageria.patientservice.dto.PatientRequestDTO;
import org.fageria.patientservice.dto.PatientResponseDTO;
import org.fageria.patientservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        List<PatientResponseDTO> dto = patientService.getAllPatient();
        return ResponseEntity.ok().body(dto);
    }
 
    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO newPatient = patientService.createPatient(patientRequestDTO);
        return ResponseEntity.ok().body(newPatient);
    }

}

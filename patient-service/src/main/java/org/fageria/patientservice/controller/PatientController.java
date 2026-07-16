package org.fageria.patientservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.fageria.patientservice.dto.PatientRequestDTO;
import org.fageria.patientservice.dto.PatientResponseDTO;
import org.fageria.patientservice.dto.validator.CreatePatientValidationGroup;
import org.fageria.patientservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "http://localhost:8080")
@Tag(name="patient",description = "API for managing patients details")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    @Operation(summary = "for fetching the details of the patients")
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        List<PatientResponseDTO> dto = patientService.getAllPatient();
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    @Operation(summary = "to create a new patient")
    public ResponseEntity<PatientResponseDTO> createPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO newPatient = patientService.createPatient(patientRequestDTO);
        return ResponseEntity.ok().body(newPatient);
    }

    @PutMapping("/{id}")
    @Operation(summary = "for updating the exists patient details")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id, @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO updatedPatient = patientService.updatePatient(id, patientRequestDTO);
        return ResponseEntity.ok().body(updatedPatient);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "to delete a patient")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

}

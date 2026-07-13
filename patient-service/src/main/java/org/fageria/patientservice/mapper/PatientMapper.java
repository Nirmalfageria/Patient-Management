package org.fageria.patientservice.mapper;

import org.fageria.patientservice.dto.PatientRequestDTO;
import org.fageria.patientservice.dto.PatientResponseDTO;
import org.fageria.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDTO toDTO(Patient patient) {
        PatientResponseDTO patientDTO = new PatientResponseDTO();
        patientDTO.setId(patient.getId().toString());
        patientDTO.setName(patient.getName());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());
        return patientDTO;
    }

    public static Patient toModel(PatientRequestDTO patientRequestDTO) {
        Patient newPatientDTO = new Patient();
        newPatientDTO.setName(patientRequestDTO.getName());
        newPatientDTO.setEmail(patientRequestDTO.getEmail());
        newPatientDTO.setAddress(patientRequestDTO.getAddress());
        newPatientDTO.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        newPatientDTO.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
        return newPatientDTO;
    }
}

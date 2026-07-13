package org.fageria.patientservice.service;

import org.fageria.patientservice.dto.PatientRequestDTO;
import org.fageria.patientservice.dto.PatientResponseDTO;
import org.fageria.patientservice.mapper.PatientMapper;
import org.fageria.patientservice.model.Patient;
import org.fageria.patientservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository repo;

    public List<PatientResponseDTO> getAllPatient() {
        List<Patient> patientList = repo.findAll();

        return patientList.stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        Patient newPatient = repo.save(PatientMapper.toModel(patientRequestDTO));
        return PatientMapper.toDTO(newPatient);
    }
}

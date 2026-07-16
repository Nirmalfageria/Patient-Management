package org.fageria.patientservice.service;

import org.fageria.patientservice.dto.PatientRequestDTO;
import org.fageria.patientservice.dto.PatientResponseDTO;
import org.fageria.patientservice.exception.EmailAlreadyExistsException;
import org.fageria.patientservice.exception.PatientNotFoundException;
import org.fageria.patientservice.mapper.PatientMapper;
import org.fageria.patientservice.model.Patient;
import org.fageria.patientservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    @Autowired
    private PatientRepository repo;

    public List<PatientResponseDTO> getAllPatient() {
        List<Patient> patientList = repo.findAll();

        return patientList.stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {

        if (repo.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A patient with this email already exists {}" + patientRequestDTO.getEmail());
        }

        Patient newPatient = repo.save(PatientMapper.toModel(patientRequestDTO));
        return PatientMapper.toDTO(newPatient);

    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
        // checking whether this user id is there or not
        Patient patient = repo.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient with this id not found " + id));
        // checking email validity
        if (repo.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("A patient with this email already exists {}" + patientRequestDTO.getEmail());
        }

        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        Patient updatedPatient = repo.save(patient);
        return PatientMapper.toDTO(updatedPatient);
    }

    public void deletePatient(UUID id) {
        repo.deleteById(id);
    }

}
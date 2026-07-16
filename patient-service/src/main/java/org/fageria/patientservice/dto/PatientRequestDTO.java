package org.fageria.patientservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.fageria.patientservice.dto.validator.CreatePatientValidationGroup;

@Data
public class PatientRequestDTO {
    @NotBlank(message = "name is required")
    @Size(max = 100, message = "name should not exceed the 100 characters")
    private String name;

    @NotBlank(message = "email is required")
    @Email(message = "Email should be vaild")
    private String email;

    @NotBlank(message = "address is required")
    private String address;

    @NotBlank(message = "DOB is required")
    private String dateOfBirth;

    @NotBlank(groups = CreatePatientValidationGroup.class ,message = "registered date is required")
    private String registeredDate;
}

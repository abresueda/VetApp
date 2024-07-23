package dev.patika.demo.dto.request.Doctor;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequest {
    @NotNull(message = "Doktor adı boş veya null olamaz.")
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
}

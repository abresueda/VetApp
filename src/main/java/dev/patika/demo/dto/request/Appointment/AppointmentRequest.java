package dev.patika.demo.dto.request.Appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequest {
    private Long id;
    private LocalDateTime appointmentDate;
    private Long doctorId;
}

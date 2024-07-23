package dev.patika.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long id;

    @Column(name = "appointment_date")
    private LocalDate appointmentDate;

    //Bir Doctor, birden çok Appointment yapabilir.Ama bir Appointment, bir Doctor tarafından yapılabilir.
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}

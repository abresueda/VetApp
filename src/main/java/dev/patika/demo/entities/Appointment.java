package dev.patika.demo.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private LocalDateTime date;

    //Bir Doctor, birden çok Appointment yapabilir.Ama bir Appointment, bir Doctor tarafından yapılabilir.
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    //Bir randevu yalnızca bir animal'a aittir.Bir animal'ın ise birden fazla randevusu olabilir.
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "animal_id")
    private Animal animal;


}

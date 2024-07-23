package dev.patika.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vaccine")
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_id")
    private Long id;

    @Column(name = "vaccine_name")
    private String name;

    @Column(name = "vaccine_code")
    private String code;

    @Column(name = "protection_strt_date")
    private LocalDate protectionStrtDate;

    @Column(name = "protection_fnsh_date")
    private LocalDate protectionFnshDate;

    //Bir Vaccine, birden çok Animal'a yapılabilir.
    @ManyToMany(mappedBy = "vaccines")
    private List<Animal> animals;
}

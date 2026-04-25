package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Data
@Table(name = "city")
@AllArgsConstructor
@NoArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "city") //Ey Hibernate, bu əlaqənin sahibi mən deyiləm, Airport entity-sindəki 'city' sahəsidir. Bazaya baxanda get Airport cədvəlinə bax.
    private List<Airport> airports;  //mappedBy olan sahə bazada sütun yaratmır. Əgər hər iki tərəfə mappedBy qoysan, nə Country cədvəlində, nə də City cədvəlində bir-birini tanıyan bir ID sütunu olmayacaq. Sütun yoxdursa, əlaqə də yoxdur.
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}

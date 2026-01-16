package com.ad.ieseljust.practica3.entitats;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "circuits")
public class Circuit implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idCircuit")
    private int idCircuit;

    @Column(name = "nom")
    private String nom;

    @Column(name = "pais")
    private String pais;

    @Column(name = "ciutat")
    private String ciutat;

    @Column(name = "longitudKm")
    private int longitudKm;

    // Constructor (sense ID perquè es genera automàticament)
    public Circuit(String nom, String pais, String ciutat, int longitudKm) {
        this.nom = nom;
        this.pais = pais;
        this.ciutat = ciutat;
        this.longitudKm = longitudKm;
    }

    // Relació molts a molts amb Pilot
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY,
            mappedBy = "circuits") // Indica aon está el mapeat
    private List<Pilot> pilots;

    public void setPilot(Pilot p){
        if(!this.pilots.contains(p)){
            this.pilots.add(p);
        }
    }
}

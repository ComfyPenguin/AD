package com.ad.ieseljust.practica3.entitats;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "equipos")
public class Equip implements Serializable {

    @Serial
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idEquip")
    private int idEquip;

    @Column (name = "nom", unique = true)
    private String nom;

    @Column (name = "seu")
    private String seu;

    @Column (name = "colorEscuderia")
    private String colorEscuderia;

    // Constructor (sense ID perquè es genera automàticament)
    public Equip(String nom, String seu, String colorEscuderia) {
        this.nom = nom;
        this.seu = seu;
        this.colorEscuderia = colorEscuderia;
    }

    // Relació un a molts amb Pilot
    @OneToMany(mappedBy = "equip", cascade = CascadeType.ALL) // Indica aon está el mapeat i el tipus de cascada
    private List<Pilot> pilots;

    public void setPilot(Pilot p){
        if(!this.pilots.contains(p)){
            this.pilots.add(p);
        }
    }
}

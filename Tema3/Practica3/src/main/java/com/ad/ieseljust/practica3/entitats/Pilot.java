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
@Table(name = "pilots")
public class Pilot implements Serializable {

    @Serial
    private static final long serialVersionUID = 137L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPilot")
    private long idPilot;

    @Column (name = "nom", unique = true)
    private String nom;

    @Column (name = "cognom", unique = true)
    private String cognom;

    @Column (name = "nacionalitat")
    private String nacionalitat;

    @Column (name = "edat")
    private int edat;

    // Constructor (sense ID perquè es genera automàticament)
    public Pilot(String nom, String cognom, String nacionalitat, int edat) {
        this.nom = nom;
        this.cognom = cognom;
        this.nacionalitat = nacionalitat;
        this.edat = edat;
    }

    // Pilot té 3 relacions,
    // un a un amb cotxe,
    // un a molts amb equips
    // i molt a molts amb circuits.

    // Relació un a un amb Cotxe
    @OneToOne(cascade = CascadeType.ALL)
    // Defineix la columna de clau forana: "idCotxe" en la taula pilots fa referència a "idCotxe" de la taula cotxes.
    @JoinColumn(name = "idCotxe", referencedColumnName = "idCotxe")
    private Cotxe cotxe;

    // Relació molts a un amb Equip
    @ManyToOne
    // Defineix la columna de clau forana: "idEquip" en la taula pilots fa referència a "idEquip" de la taula cotxes.
    @JoinColumn(name = "idEquip", referencedColumnName = "idEquip")
    private Equip equip;

    // Relació molts a molts amb Circuit
    @ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
    @JoinTable(name="carreres",
          joinColumns = {@JoinColumn(
            name="idPilot",
            foreignKey = @ForeignKey(name = "FK_CAR_PIL" ))},
          inverseJoinColumns = {@JoinColumn(
            name="idCircuit",
            foreignKey = @ForeignKey(name = "FK_CAR_CIR" ))})
    private List<Circuit> circuits;

    public void setCircuit(Circuit c){
        if(!this.circuits.contains(c)){
            this.circuits.add(c);
        }
    }

}


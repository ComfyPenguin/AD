package com.ad.ieseljust.practica2.objectes;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "pilotos")
public class Pilot implements Serializable {

    public Pilot(String nom, Equip equip, String pais, int edat, int victories) {
        this.nom = nom;
        this.equip = equip;
        this.pais = pais;
        this.edat = edat;
        this.victories = victories;
    }

    @Serial
    private static final long serialVersionUID = 137L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPiloto")
    private long idPilot;

    @Column (name = "nombre")
    private String nom;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
      name="idEquipo",
      referencedColumnName = "idEquipo",
      foreignKey = @ForeignKey(name = "FK_PILOTO_EQUIPO"))
    private Equip equip;

    @Column
    private String pais;

    @Column (name = "edad")
    private int edat;

    @Column (name = "victorias")
    private int victories;
}


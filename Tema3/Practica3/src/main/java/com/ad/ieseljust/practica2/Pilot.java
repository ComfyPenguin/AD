package com.ad.ieseljust.practica2;

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

    public Pilot(String nom, String equip, String pais, int edat, double punts, int podios, int victories, boolean actiu, double altura, double vuelta_rapida) {
        this.nom = nom;
        this.equip = equip;
        this.pais = pais;
        this.edat = edat;
        this.punts = punts;
        this.podios = podios;
        this.victories = victories;
        this.actiu = actiu;
        this.altura = altura;
        this.vuelta_rapida = vuelta_rapida;
    }

    @Serial
    private static final long serialVersionUID = 137L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPiloto;

    @Column (name = "nombre")
    private String nom;

    @Column (name = "equipo")
    private String equip;

    @Column
    private String pais;

    @Column (name = "edad")
    private int edat;

    @Column (name = "puntos")
    private double punts;

    @Column
    private int podios;

    @Column (name = "victorias")
    private int victories;

    @Column (name = "activo")
    private boolean actiu;

    @Column (name = "altura_m")
    private double altura;

    @Column (name = "vuelta_rapida_seg")
    private double vuelta_rapida;
}


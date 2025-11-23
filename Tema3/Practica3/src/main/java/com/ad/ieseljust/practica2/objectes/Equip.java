package com.ad.ieseljust.practica2.objectes;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "equipos")
public class Equip implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idEquipo")
    private int idEquip;

    @Column (name = "nombre")
    private String nom;

    public Equip(String nom) {
        this.nom = nom;
    }
}

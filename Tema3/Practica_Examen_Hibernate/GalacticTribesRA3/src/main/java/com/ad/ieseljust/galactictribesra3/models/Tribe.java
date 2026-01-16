/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ad.ieseljust.galactictribesra3.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jasb
 */

@NamedQuery(name = "Tribe.findAll", query = "SELECT t FROM Tribe t")
@Data
@NoArgsConstructor
@Entity
@Table
public class Tribe implements Serializable {

    @Serial
    private static final long serialVersionUID = 17L;

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long tribe_id;

    @Column
    private String name;

    @Column
    private int members;

    // Relación M:N con Planet
    @ToString.Exclude
    @ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
    @JoinTable(name="Ecosystem", // Nombre de la tabla intermedia en la BD
          joinColumns = {@JoinColumn( // Columna que referencia a Gift en la tabla intermedi
            name="tribe",  // Nombre de la columna FK para Gift
            foreignKey = @ForeignKey(name = "FK_TRIPLA_TRI" ))}, // Nombre de la constraint FK
          inverseJoinColumns = {@JoinColumn(
            name="planet",
            foreignKey = @ForeignKey(name = "FK_TRIPLA_PLA" ))})
    private List<Planet> planets;

    // Relación M:N con Battle
    @ToString.Exclude
    @ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
    @JoinTable(name="History_Fights", // Nombre de la tabla intermedia en la BD
          joinColumns = {@JoinColumn( // Columna que referencia a Gift en la tabla intermedi
            name="tribe",  // Nombre de la columna FK para Gift
            foreignKey = @ForeignKey(name = "FK_TRIBAT_TRI" ))}, // Nombre de la constraint FK
          inverseJoinColumns = {@JoinColumn(
            name="battle",
            foreignKey = @ForeignKey(name = "FK_TRIBAT_BAT" ))})
    private List<Battle> battles;

    // Relación 1:M con Player
    @ToString.Exclude
    @OneToMany(mappedBy = "myTribe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Player> players;

    // Relación 1:M con Battle
    @ToString.Exclude
    @OneToMany(mappedBy = "winner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Battle> victories;
}

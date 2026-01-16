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

/**
 *
 * @author jasb
 */

@NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p")
@Data
@NoArgsConstructor
@Entity
@Table
public class Player implements Serializable {

    @Serial
    private static final long serialVersionUID = 17L;

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long player_id;

    @Column
    private String name;

    @Column
    private int points;

    // Relación M:1 con Tribe
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "myTribe", referencedColumnName = "tribe_id")
    private Tribe myTribe;

    // Relacion 1:1 con Weapon
    @ToString.Exclude
    @OneToOne(mappedBy = "owner")
    private Weapon weapon;
}

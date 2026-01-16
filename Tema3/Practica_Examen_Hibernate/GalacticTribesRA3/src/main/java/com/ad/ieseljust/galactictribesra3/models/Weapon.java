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

@Data
@NoArgsConstructor
@Entity
@Table
public class Weapon implements Serializable {

    @Serial
    private static final long serialVersionUID = 17L;

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long weapon_id;

    @Column
    private String name;

    @Column
    private int damage;

    @Column
    private String type;

    // Relacion 1:1 con Player
    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner", referencedColumnName = "player_id")
    private Player owner;

}

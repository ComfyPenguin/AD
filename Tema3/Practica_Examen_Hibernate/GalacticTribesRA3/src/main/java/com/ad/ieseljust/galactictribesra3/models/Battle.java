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

@Data
@NoArgsConstructor
@Entity
@Table
public class Battle implements Serializable {

    @Serial
    private static final long serialVersionUID = 17L;

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long battle_id;

    @Column
    private String location;

    // Relación M:1 con Tribe
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "winner", referencedColumnName = "tribe_id")
    private Tribe winner;

    // Relación M:N con Tribe
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY,
            mappedBy = "battles") // Indica donde está el mapeado
    private List<Tribe> tribes;
}

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

@NamedQuery(name = "Planet.findAll", query = "SELECT p FROM Planet p")
@Data
@NoArgsConstructor
@Entity
@Table
public class Planet implements Serializable {

    @Serial
    private static final long serialVersionUID = 17L;

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long planet_id;

    @Column
    private String name;

    @Column
    private String environment_type;

    // Relación M:N con Tribe
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY,
            mappedBy = "planets") // Indica donde está el mapeado
    private List<Tribe> tribes;
}

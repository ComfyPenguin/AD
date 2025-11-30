package com.ad.ieseljust.practica2.entitats;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cotxes")
public class Cotxe implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idCotxe")
    private int idCotxe;

    @Column(name = "motor", unique = true)
    private String motor;

    @Column(name = "potenciaCV")
    private String potenciaCV;

    // Constructor (sense ID perquè es genera automàticament)
    public Cotxe(String motor, String potenciaCV) {
        this.motor = motor;
        this.potenciaCV = potenciaCV;
    }

    // Relació un a un amb Pilot
    @OneToOne(mappedBy = "cotxe") // Indica aon está el mapeat
    private Pilot pilot;
}

package t3.activitat.xmas.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Camel")
public class Camel implements Serializable {

    @Serial
    private static final long serialVersionUID = 137L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "camel_id")
    private Long idCamel;

    @Column(name = "name")
    private String nom;

    // Relació un a un amb MagicKing
    @ToString.Exclude
    @OneToOne(mappedBy = "camel") // Indica aon está el mapeat
    private MagicKing magicKing;

}

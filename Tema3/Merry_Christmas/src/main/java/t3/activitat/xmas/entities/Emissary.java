package t3.activitat.xmas.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Emissary")
public class Emissary implements Serializable {

    @Serial
    private static final long serialVersionUID = 137L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "emissary_id")
    private Long idEmissary;

    @Column(name = "name")
    private String name;

    // Relació molts a un amb MagicKing
    @ToString.Exclude
    @ManyToOne
    // Defineix la columna de clau forana: "workFor" en la taula Emissary fa referencia a "id_magicking" de la taula MagicKing.
    @JoinColumn(name = "workFor", referencedColumnName = "id_magicking")
    private MagicKing magicKing;

    // Relació molts a molts amb Gift
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY,
            mappedBy = "emissaries") // Indica aon está el mapeat
    private List<Gift> gifts;

}

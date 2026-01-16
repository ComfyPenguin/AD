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
@Table(name = "MagicKing")
public class MagicKing implements Serializable {

    @Serial
    private static final long serialVersionUID = 137L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_magicking")
    private Integer idMagicKing;

    @Column(name = "name")
    private String name;

    // Relació un a un amb Camel
    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    // Defineix la columna de clau forana: "camel" en la taula MagicKing fa referència a "camel_id" de la taula Camel.
    @JoinColumn(name = "camel", referencedColumnName = "camel_id")
    private Camel camel;

    // Relació un a molts amb Emissary
    @ToString.Exclude
    @OneToMany(mappedBy = "magicKing", cascade = CascadeType.ALL) // Indica aon está el mapeat i el tipus de cascada
    private List<Emissary> emissaries;

    // Relació un a molts amb Child
    @ToString.Exclude
    @OneToMany(mappedBy = "myKing", cascade = CascadeType.ALL) // Indica aon está el mapeat i el tipus de cascada
    private List<Child> children;
}

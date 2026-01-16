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
@Table(name = "Child")
public class Child implements Serializable {

    @Serial
    private static final long serialVersionUID = 137L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "child_id")
    private int idChild;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "isNice")
    private Boolean isNice;

    // Relació molts a un amb MagicKing
    @ToString.Exclude
    @ManyToOne
    // Defineix la columna de clau forana: "myKing" en la taula Child fa referència a "id_magicking" de la taula MagicKing.
    @JoinColumn(name = "myKing", referencedColumnName = "id_magicking")
    private MagicKing myKing;

    // Relació un a molts amb Gift
    @ToString.Exclude
    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL) // Indica aon está el mapeat i el tipus de cascada
    private List<Gift> gifts;


}

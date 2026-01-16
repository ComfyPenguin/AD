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
@Table(name = "Paje")
public class Paje implements Serializable {

    @Serial
    private static final long serialVersionUID = 137L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "paje_id")
    private Long idPaje;

    @Column(name = "name")
    private String name;

    // Relació molts a molts amb Gift
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY,
            mappedBy = "pajes") // Indica aon está el mapeat
    private List<Gift> gifts;

}

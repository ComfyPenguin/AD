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
@Table(name = "Gift")
public class Gift implements Serializable {

    @Serial
    private static final long serialVersionUID = 137L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_gift")
    private Long idGift;

    @Column(name = "giftName")
    private String name;

    // Relació molts a un amb Child
    @ToString.Exclude
    @ManyToOne
    // Defineix la columna de clau forana: "child" en la taula Gift fa referencia a "child_id" de la taula Child.
    @JoinColumn(name = "child", referencedColumnName = "child_id")
    private Child child;

    @Column(name = "isPackaged")
    private Boolean isPackaged;

    @Column(name = "isSent")
    private Boolean isSent;

    // 2 relacions molts a molts amb emissary i paje
    // Relació molts a molts amb Paje
    @ToString.Exclude
    @ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
    @JoinTable(name="Packaging", // Nom de la taula intermedia en la BD
          joinColumns = {@JoinColumn( // Columna que referencia a Gift en la taula intermedia
            name="gift", // Nom de la columna FK per a Gift
            foreignKey = @ForeignKey(name = "FK_PAC_GIF" ))}, // Nom de la constraint FK
          inverseJoinColumns = {@JoinColumn(
            name="paje",
            foreignKey = @ForeignKey(name = "FK_PAC_PAJ" ))})
    private List<Paje> pajes;


    // Relació molts a molts amb Emissary
    @ToString.Exclude
    @ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
    @JoinTable(name="GiftDelivery", // Nom de la taula intermedia en la BD
          joinColumns = {@JoinColumn( // Columna que referencia a Gift en la taula intermedia
            name="gift", // Nom de la columna FK per a Gift
            foreignKey = @ForeignKey(name = "FK_GIFDEL_GIF" ))}, // Nom de la constraint FK
          inverseJoinColumns = {@JoinColumn(
            name="emissary",
            foreignKey = @ForeignKey(name = "FK_GIFDEL_EMI" ))})
    private List<Emissary> emissaries;
}

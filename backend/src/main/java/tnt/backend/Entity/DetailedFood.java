package tnt.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "detailedfood")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailedFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_invoice")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "id_food")
    private Food food;

    @Column(name = "quantity")
    private  int quantity;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    @ManyToOne
    @JoinColumn(name = "creator")
    private Employee creator;

    @ManyToOne
    @JoinColumn(name = "editor")
    private Employee editor;

    @Column(name = "status")
    private boolean status ;


}

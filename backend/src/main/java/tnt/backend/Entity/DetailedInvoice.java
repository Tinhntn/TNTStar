package tnt.backend.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "detailedinvoice")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailedInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_invoice")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "id_ticket")
    private Ticket ticket;

    @Column(name = "quantity")
    private int quantity;

    @NotEmpty(message = "Creator can't be empty")
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
    private boolean status;
}

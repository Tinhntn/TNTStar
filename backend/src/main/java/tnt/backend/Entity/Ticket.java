package tnt.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ticket_code")
    private  String ticketCode;

    @OneToOne
    @JoinColumn(name = "id_chair")
    private Chairs chairs;

    @Column(name = "price")
    private  double price;

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

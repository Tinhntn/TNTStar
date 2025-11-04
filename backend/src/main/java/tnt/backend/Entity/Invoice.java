package tnt.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invoice")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "invoice_code")
    private  String invoiceCode;

    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "payment_type")
    private  String paymentType;

    @Column(name = "qrcode")
    private  String qrcode;

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

    @OneToMany(mappedBy = "invoice")
    private List<DetailedInvoice> detailedInvoices  = new ArrayList<>();

    @OneToMany(mappedBy = "invoice")
    private List<DetailedFood> detailedFoods  = new ArrayList<>();

}

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
@Table(name="customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_voucher")
    private Voucher voucher;

    @Column(name = "customer_code")
    private String customerCode;

    @Column(name = "first_name")
    private  String firstName;

    @Column(name = "last_name")
    private  String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private  String password;

    @Column(name = "point")
    private  int point;

    @Column(name = "address")
    private  String address;

    @Column(name = "membership_level")
    private  int membershipLevel;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    @Column(name = "status")
    private boolean status;

    @OneToMany(mappedBy = "customer")
    private List<History> histories;

    @OneToMany(mappedBy = "customer")
    private List<Invoice> invoices  = new ArrayList<>();
}

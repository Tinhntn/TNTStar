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
@Table(name = "director")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "director_code")
    private String directorCode;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    @Column(name = "descibe")
    private String depcription;

    @Column(name = "status")
    private boolean status;

    @OneToMany(mappedBy = "director")
    private List<Movie> movies  = new ArrayList<>();
}

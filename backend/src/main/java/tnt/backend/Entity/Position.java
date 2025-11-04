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
@Table (name = "position")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "position_code")
    private String positionCode;

    @Column(name = "position_name")
    private String positionName;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    @Column(name = "status")
    private boolean status;

    public Position(String positionCode, String positionName, LocalDate createdDate, LocalDate modifiedDate, boolean status) {
        this.positionCode = positionCode;
        this.positionName = positionName;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.status = status;
    }
    @OneToMany(mappedBy = "position")
    private List<Employee> employees  = new ArrayList<>();
}

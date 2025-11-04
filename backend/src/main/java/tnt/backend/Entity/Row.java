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
@Table(name = "roww")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Row {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_room")
    private Room room;

    @Column(name = "row_name")
    private String rowName;

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

    @OneToMany(mappedBy = "row")
    private List<Chairs> chairs  = new ArrayList<>();

}

package tnt.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tnt.backend.DTO.request.ChairsDTO;

import java.time.LocalDate;

@Entity
@Table(name = "chairs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Chairs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_room")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "id_row")
    private Row row;

    @Column(name = "chair_name")
    private String chairName;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    @ManyToOne
    @JoinColumn (name = "creator")
    private Employee creator;

    @ManyToOne
    @JoinColumn(name = "editor")
    private Employee editor;

    @Column(name = "status")
    private boolean status;

    public Chairs(ChairsDTO chairsDTO, Room room, Row row, Employee creator) {
        this.room =room;
        this.row = row;
        this.chairName = chairsDTO.getChairName();
        this.creator = creator;
        this.status = chairsDTO.isStatus();
    }
}

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
@Table(name = "shows")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shows {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "show_code")
    private  String showCode;

    @ManyToOne
    @JoinColumn(name = "id_movie")
    private Movie movie;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

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

    @OneToMany(mappedBy = "shows")
    private List<Room> rooms  = new ArrayList<>();
}

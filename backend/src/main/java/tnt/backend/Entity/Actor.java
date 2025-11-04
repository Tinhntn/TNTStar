package tnt.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tnt.backend.DTO.request.ActorDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "actor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "actor_code")
    private String actorCode;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    @Column(name = "`describe`")
    private String describe;

    @Column(name = "status")
    private boolean status;

    @OneToMany(mappedBy = "actor")
    private List<MovieActor> moviceActors = new ArrayList<>();

    public void loadActor(ActorDTO actorDTO) {
        this.fullName = actorDTO.getFullName();
        this.dateOfBirth = actorDTO.getDateOfBirth();
        this.createdDate = actorDTO.getCreatedDate();
        this.modifiedDate = actorDTO.getModifiedDate();
        this.describe = actorDTO.getDescribe();
        this.status = actorDTO.isStatus();
    }
}

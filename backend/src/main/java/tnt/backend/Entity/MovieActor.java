package tnt.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tnt.backend.Service.Implement.MovieActorId;

import java.time.LocalDate;

@Entity
@Table(name = "movie_actor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieActor {
    @EmbeddedId
    private MovieActorId id;

    @ManyToOne
    @MapsId("idMovie")
    @JoinColumn(name = "id_movie")
    private Movie movie;

    @ManyToOne
    @MapsId("idActor")
    @JoinColumn(name = "id_actor")
    private Actor actor;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    @Column(name = "status")
    private boolean status;
}

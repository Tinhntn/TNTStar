package tnt.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tnt.backend.Service.Implement.MovieLauguageId;

import java.time.LocalDate;

@Entity
@Table(name = "movie_language")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieLanguage {
    @EmbeddedId
    private MovieLauguageId id;

    @ManyToOne
    @MapsId("idMovie")
    @JoinColumn(name = "id_movie")
    private Movie movie;

    @ManyToOne
    @MapsId("idLanguage")
    @JoinColumn(name = "id_language")
    private Language language;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    @Column(name = "status")
    private boolean status;
}

package tnt.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tnt.backend.Service.Implement.MovieFormatId;

import java.time.LocalDate;

@Entity
@Table(name = "movie_format")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieFormat {
    @EmbeddedId
    private MovieFormatId id;

    @ManyToOne
    @MapsId("idMovie")
    @JoinColumn(name = "id_movie")
    private Movie movie;

    @ManyToOne
    @MapsId("idFormat")
    @JoinColumn(name = "id_format")
    private Format format;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    @Column(name = "status")
    private boolean status;
}

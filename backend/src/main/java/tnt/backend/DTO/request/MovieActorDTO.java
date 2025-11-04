package tnt.backend.DTO.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tnt.backend.Entity.Actor;
import tnt.backend.Entity.Movie;
import tnt.backend.Entity.MovieActor;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieActorDTO {
    @NotEmpty(message = "Movie can't be empty")
    @Min(value = 0, message = "Invalid Movie id!")
    private int idMovie;

    @NotEmpty(message = "Actor can't be empty")
    @Min(value = 0, message = "Invalid Actor id!")
    private int idActor;

    @NotEmpty(message = "Creator can't be empty")
    @JsonFormat(shape =  JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate modifiedDate;

    private boolean status;

    public MovieActorDTO(MovieActor movieActor){
        this.idMovie = movieActor.getMovie().getId();
        this.idActor = movieActor.getActor().getId();
        this.createdDate = movieActor.getCreatedDate();
        this.modifiedDate = movieActor.getModifiedDate();
        this.status = movieActor.isStatus();
    }
}

package tnt.backend.DTO.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tnt.backend.Entity.Director;
import tnt.backend.Entity.Genre;
import tnt.backend.Entity.Movie;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    @NotNull(message = "Director can't be empty")
    @Min(value = 0,message = "Invalid Director id!")
    private int idDirector;

    @NotNull(message = "Genre can't be empty")
    @Min(value = 0,message = "Invalid Genre id!")
    private int idGenre;

    @NotEmpty(message = "Movie code can't be empty")
    @Size(min = 1, max = 50, message = "Movie code exceed the allowed number of characters")
    private String movieCode;

    @NotEmpty(message = "Movie name can't be empty")
    @Size(min=0, max = 255, message = "Movie name exceed the allowed number of characters")
    private String movieName;

    @NotEmpty(message = "Durations can't be empty")
    @Size(min=0, max = 255, message = "Durations exceed the allowed number of characters")
    private String duration;

    @NotNull(message = "Release time can't be empty")
    @Future(message = "Reslease time date must be start in the future")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate releaseTime;

    @NotNull(message = "Background imgae can't be empty")
    @Size(min = 1, max = 100,message = "Background image exceed the allowed number of characters")
    private String backgroundImage;

    @NotNull(message = "Image can't be empty")
    @Size(min = 1, max = 255,message = "Image exceed the allowed number of characters")
    private String image;

    @NotNull(message = "Trailer can't be empty")
    @Size(min = 1, max = 255,message = "Trailer exceed the allowed number of characters")
    private String trailer;

    @Size(min = 0, max = 1000, message = "Depcription exceed the allowed number of characters")
    private String depcription;

    @Min(value = 0, message = "Invalid age")
    private Integer ageLimit;

    @NotNull(message = " Create date can't be empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate modifiedDate;

    private boolean status;

    public MovieDTO(Movie movie) {
        this.idDirector = movie.getDirector().getId();
        this.idGenre = movie.getGenre().getId();
        this.movieCode = movie.getMovieCode();
        this.movieName = movie.getMovieName();
        this.duration = movie.getDuration();
        this.releaseTime = movie.getReleaseTime();
        this.backgroundImage = movie.getBackgroundImage();
        this.image = movie.getImage();
        this.trailer = movie.getTrailer();
        this.depcription = movie.getDepcription();
        this.ageLimit = movie.getAgeLimit();
        this.createdDate = movie.getCreatedDate();
        this.modifiedDate = movie.getModifiedDate();
        this.status = movie.isStatus();
    }
}

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
import tnt.backend.Entity.Employee;
import tnt.backend.Entity.Movie;
import tnt.backend.Entity.Shows;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShowsDTO {
    @NotEmpty(message = "Show code can't be empty")
    @Size(min = 1, max = 50, message = "Show code exceed the allowed number of characters")
    private  String showCode;

    @NotEmpty(message = "Movie can't be empty")
    @Min(value = 0, message = "Invalid Movie id!")
    private int idMovie;

    @NotNull(message = "Release time can't be empty")
    @Future(message = "Reslease time date must be start in the future")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;

    @NotEmpty(message = "Start time can't be empty")
    @Size(min=0, max = 255, message = "Start time exceed the allowed number of characters")
    private String startTime;

    @NotEmpty(message = "End time can't be empty")
    @Size(min=0, max = 255, message = "End time exceed the allowed number of characters")
    private String endTime;


    @NotEmpty(message = "Creator can't be empty")
    @Min(value = 0, message = "Invalid creator id!")
    private int idCreator;

    @Min(value = 0, message = "Invalid creator id!")
    private int idEditor;

    private boolean status;

    public ShowsDTO(Shows shows){
        this.showCode = shows.getShowCode();
        this.idMovie = shows.getMovie().getId();
        this.releaseDate = shows.getReleaseDate();
        this.startTime = shows.getStartTime();
        this.endTime = shows.getEndTime();
        this.idCreator = shows.getCreator().getId();
        this.status = shows.isStatus();
    }
}

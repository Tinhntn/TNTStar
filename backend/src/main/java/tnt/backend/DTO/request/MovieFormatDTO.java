package tnt.backend.DTO.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tnt.backend.Entity.MovieFormat;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieFormatDTO {
    @NotEmpty(message = "Movie can't be empty")
    @Min(value = 0, message = "Invalid Movie id!")
    private int idMovie;

    @NotEmpty(message = "Format can't be empty")
    @Min(value = 0, message = "Invalid Format id!")
    private int idFormat;

    @NotEmpty(message = "Creator can't be empty")
    @JsonFormat(shape =  JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate modifiedDate;

    private boolean status;

    public MovieFormatDTO(MovieFormat movieFormat){
        this.idMovie = movieFormat.getMovie().getId();
        this.idFormat = movieFormat.getFormat().getId();
        this.createdDate = movieFormat.getCreatedDate();
        this.modifiedDate = movieFormat.getModifiedDate();
        this.status = movieFormat.isStatus();
    }
}

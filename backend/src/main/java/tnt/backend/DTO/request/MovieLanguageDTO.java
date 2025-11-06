package tnt.backend.DTO.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tnt.backend.Entity.MovieLanguage;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieLanguageDTO {
    @NotEmpty(message = "Movie can't be empty")
    @Min(value = 0, message = "Invalid Movie id!")
    private int idMovie;

    @NotEmpty(message = "Language can't be empty")
    @Min(value = 0, message = "Invalid Language id!")
    private int idLanguage;


    private boolean status;

    public MovieLanguageDTO(MovieLanguage movieLanguage) {
        this.idMovie = movieLanguage.getMovie().getId();
        this.idLanguage = movieLanguage.getLanguage().getId();
        this.status = movieLanguage.isStatus();
    }
}

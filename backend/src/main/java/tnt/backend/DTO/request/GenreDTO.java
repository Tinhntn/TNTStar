package tnt.backend.DTO.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tnt.backend.Entity.Genre;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenreDTO {

    @NotEmpty(message = "Genre code can't be empty")
    @Size(min = 1, max = 50, message = "Food code exceed the allowed number of characters")
    private  String genreCode;

    @NotEmpty(message = "Genre name can't be empty")
    @Size(min = 1, max = 255, message = "Food name exceed the allowed number of characters")
    private  String genreName;


    private boolean status;

    public GenreDTO(Genre genre) {
        this.genreCode = genre.getGenreCode();
        this.genreName = genre.getGenreName();
        this.status = genre.isStatus();
    }
}

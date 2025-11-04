package tnt.backend.DTO.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tnt.backend.Entity.Director;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DirectorDTO {

    @NotEmpty(message = "Director code can't be empty")
    @Size(min = 1, max = 50, message = "Director code exceed the allowed number of characters")
    private String directorCode;

    @NotEmpty(message = "Director fullname can't be empty")
    @Size(min = 0, max = 255, message = "Director fullname exceed the allowed number of characters")
    private String fullName;

    @NotNull(message = "Date of birth can't be empty")
    @Past(message = "Date of birth must be in the past")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    @NotNull(message = " Create date can't be empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate modifiedDate;

    @NotEmpty(message = "Director depcription can't be empty")
    @Size(min = 1, max = 255,message = "Depcription exceed the allowed number of charactors")
    private String depcription;

    private boolean status;

    public DirectorDTO(Director director) {
        this.directorCode = director.getDirectorCode();
        this.fullName = director.getFullname();
        this.dateOfBirth = director.getDateOfBirth();
        this.createdDate = director.getCreatedDate();
        this.modifiedDate = director.getModifiedDate();
        this.depcription = director.getDepcription();
        this.status = director.isStatus();
    }
}

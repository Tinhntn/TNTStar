package tnt.backend.DTO.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tnt.backend.Entity.Actor;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActorDTO {
    @NotEmpty(message = "Actor code can't be empty")
    @Size(min = 1, max = 50, message = "Actor code exceed the allowed number of characters")
    private String actorCode;

    @NotEmpty(message = "Full name can't be blank")
    @Size(min = 1, max = 255,message = "Full name exceed the allowed number of characters ")
    private String fullName;

    @Past(message = "Actor's date of birth must be in the past")
    @NotNull(message = "Date of birth can't be blank")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    @NotNull(message = " Create date can't be empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate createdDate   ;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate modifiedDate;

    @Size(min = 0, max = 1000, message = "Depcription exceed the allowed number of characters")
    private String describe;

    private boolean status;

    public void loadActorDTO(Actor actor){
        this.actorCode = actor.getActorCode();
        this.fullName = actor.getFullName();
        this.dateOfBirth = actor.getDateOfBirth();
        this.createdDate = actor.getCreatedDate();
        this.describe = actor.getDescribe();
        this.status = actor.isStatus();
    }
}

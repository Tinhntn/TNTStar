package tnt.backend.DTO.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import tnt.backend.Entity.Cinema;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CinemaDTO {

    @NotEmpty(message = "Cinema code can't be empty")
    @Size(min = 1, max = 50, message = "Cinema code exceed the allowed number of characters")
    private String cinemaCode;

    @NotEmpty(message = "Cinema name can't be empty")
    @Size(min = 1, max = 255, message = "Cinema name exceed the allowed number of characters")
    private String cinemaName;

    @NotEmpty(message = "Address can't be empty")
    @Size(min = 1, max = 255, message = "Address exceed the allowed number of characters")
    private String address;

    @NotNull(message = " Create date can't be empty")
    @JsonFormat(shape =  JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate modifiedDate;

    @NotEmpty(message = "Creator can't be empty")
    @Min(value = 0, message = "Invalid creator id!")
    private int idCreator;

    @Min(value = 0, message = "Invalid creator id!")
    private int idEditor;

    private boolean status;

    public CinemaDTO(Cinema cinema) {
        this.cinemaCode = cinema.getCinemaCode();
        this.cinemaName = cinema.getCinemaName();
        this.address = cinema.getAddress();
        this.createdDate = cinema.getCreatedDate();
        this.modifiedDate = cinema.getModifiedDate();
        this.idCreator = cinema.getCreator().getId();
        this.idEditor = cinema.getEditor().getId();
        this.status = cinema.isStatus();
    }
}

package tnt.backend.DTO.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tnt.backend.Entity.Cinema;
import tnt.backend.Entity.Employee;
import tnt.backend.Entity.Room;
import tnt.backend.Entity.Shows;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    @NotEmpty(message = "Cinema can't be empty")
    @Min(value = 0, message = "Invalid Cinema id!")
    private int idCinema;

    @NotEmpty(message = "Show can't be empty")
    @Min(value = 0, message = "Invalid Show id!")
    private int idShow;

    @NotEmpty(message = "Room code can't be empty")
    @Size(min = 1, max = 50, message = "Room code exceed the allowed number of characters")
    private String roomCode;

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

    public RoomDTO(Room room) {
        this.idCinema = room.getCinema().getId();
        this.idShow = room.getShows().getId();
        this.roomCode = room.getRoomCode();
        this.createdDate = room.getCreatedDate();
        this.modifiedDate = room.getModifiedDate();
        this.idCreator = room.getCreator().getId();
        this.idEditor = room.getEditor().getId();
        this.status = room.isStatus();
    }
}
